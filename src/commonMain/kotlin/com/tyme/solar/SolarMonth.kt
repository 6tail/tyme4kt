package com.tyme.solar

import com.tyme.unit.AbstractDay
import com.tyme.unit.AbstractMonth
import com.tyme.unit.AbstractYear
import kotlin.jvm.JvmStatic

/**
 * 公历月
 *
 * @author 6tail
 */
class SolarMonth(year: Int, month: Int): AbstractMonth(year, month) {

    init {
        validate(year, month)
    }

    /**
     * 公历年
     */
    val solarYear: SolarYear
        get() = SolarYear(year)

    override val abstractYear: AbstractYear
        get() = solarYear

    override val dayCount: Int
        get() = getDayCount(year, month)

    /**
     * 位于当年的索引(0-11)
     */
    val indexInYear: Int
        get() = month - 1

    /**
     * 公历季度
     *
     * @return 公历季度
     */
    fun getSeason(): SolarSeason {
        return SolarSeason(year, indexInYear / 3)
    }

    override fun getName(): String {
        return NAMES[indexInYear]
    }

    override fun next(n: Int): SolarMonth {
        val i: Int = month - 1 + n
        return SolarMonth((year * 12 + i) / 12, indexOf(i, 12) + 1)
    }

    /**
     * 本月的公历周列表
     *
     * @param start 星期几作为一周的开始，1234560分别代表星期一至星期天
     * @return 周列表
     */
    fun getWeeks(start: Int): List<SolarWeek> {
        val size: Int = getWeekCount(start)
        val l: MutableList<SolarWeek> = ArrayList(size)
        for (i in 0 until size) {
            l.add(SolarWeek(year, month, i, start))
        }
        return l
    }

    /**
     * 本月的公历日列表
     *
     * @return 公历日列表
     */
    fun getDays(): List<SolarDay> {
        val size: Int = dayCount
        val l: MutableList<SolarDay> = ArrayList(size)
        for (i in 1..size) {
            l.add(SolarDay(year, month, i))
        }
        return l
    }

    override val firstDay: AbstractDay
        get() = SolarDay(year, month, 1)

    companion object {
        val NAMES: Array<String> = arrayOf("1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月")

        /**
         * 每月天数
         */
        val DAYS: IntArray = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

        @JvmStatic
        fun validate(year: Int, month: Int) {
            validateRange(month, 1, 12, "solar month")
            SolarYear.validate(year)
        }

        @JvmStatic
        fun fromYm(year: Int, month: Int): SolarMonth {
            return SolarMonth(year, month)
        }

        /**
         * 天数
         *
         * @param year  年
         * @param month 月
         * @return 天数
         */
        @JvmStatic
        fun getDayCount(year: Int, month: Int): Int {
            // 1582年10月只有21天
            if (1582 == year && 10 == month) {
                return 21
            }
            var d = DAYS[month - 1]
            //公历闰年2月多一天
            if (2 == month && SolarYear.isLeap(year)) {
                d++
            }
            return d
        }
    }
}

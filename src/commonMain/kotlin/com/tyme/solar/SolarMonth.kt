package com.tyme.solar

import com.tyme.AbstractTyme
import kotlin.jvm.JvmStatic
import kotlin.math.ceil

/**
 * 公历月
 *
 * @author 6tail
 */
class SolarMonth(
    /** 年 */
    year: Int,
    /** 月 */
    private var month: Int
) : AbstractTyme() {

    /** 年 */
    protected var year: SolarYear

    init {
        require(month in 1..12) { "illegal solar month: $month" }
        this.year = SolarYear(year)
    }

    /**
     * 公历年
     *
     * @return 公历年
     */
    fun getSolarYear(): SolarYear {
        return year
    }

    /**
     * 年
     *
     * @return 年
     */
    fun getYear(): Int {
        return year.getYear()
    }

    /**
     * 月
     *
     * @return 月
     */
    fun getMonth(): Int {
        return month
    }

    /**
     * 天数（1582年10月只有21天)
     *
     * @return 天数
     */
    fun getDayCount(): Int {
        if (1582 == getYear() && 10 == month) {
            return 21
        }
        var d = DAYS[getIndexInYear()]
        //公历闰年2月多一天
        if (2 == month && year.isLeap()) {
            d++
        }
        return d
    }

    /**
     * 位于当年的索引(0-11)
     *
     * @return 索引
     */
    fun getIndexInYear(): Int {
        return month - 1
    }

    /**
     * 公历季度
     *
     * @return 公历季度
     */
    fun getSeason(): SolarSeason {
        return SolarSeason(getYear(), getIndexInYear() / 3)
    }

    /**
     * 周数
     *
     * @param start 起始星期，1234560分别代表星期一至星期天
     * @return 周数
     */
    fun getWeekCount(start: Int): Int {
        return ceil((indexOf(SolarDay(getYear(), month, 1).getWeek().getIndex() - start, 7) + getDayCount()) / 7.0).toInt()
    }

    override fun getName(): String {
        return NAMES[getIndexInYear()]
    }

    override fun toString(): String {
        return "${year}${getName()}"
    }

    override fun next(n: Int): SolarMonth {
        val i = month - 1 + n
        return SolarMonth((getYear() * 12 + i) / 12, indexOf(i, 12) + 1)
    }

    /**
     * 本月的公历周列表
     *
     * @param start 星期几作为一周的开始，1234560分别代表星期一至星期天
     * @return 周列表
     */
    fun getWeeks(start: Int): List<SolarWeek> {
        val size = getWeekCount(start)
        val y = getYear()
        val l: MutableList<SolarWeek> = ArrayList(size)
        for (i in 0 until size) {
            l.add(SolarWeek(y, month, i, start))
        }
        return l
    }

    /**
     * 本月的公历日列表
     *
     * @return 公历日列表
     */
    fun getDays(): List<SolarDay> {
        val size = getDayCount()
        val y = getYear()
        val l: MutableList<SolarDay> = ArrayList(size)
        for (i in 1..size) {
            l.add(SolarDay(y, month, i))
        }
        return l
    }

    override fun equals(other: Any?): Boolean {
        return other is SolarMonth && toString() == other.toString()
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    companion object {
        val NAMES: Array<String> = arrayOf("1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月")

        /** 每月天数 */
        val DAYS: IntArray = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

        @JvmStatic
        fun fromYm(year: Int, month: Int): SolarMonth {
            return SolarMonth(year, month)
        }
    }
}

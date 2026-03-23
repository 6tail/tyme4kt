package com.tyme.rabbyung

import com.tyme.solar.SolarDay
import com.tyme.unit.DayUnit
import kotlin.jvm.JvmStatic
import kotlin.math.abs

/**
 * 藏历日，仅支持藏历1950年十二月初一（公历1951年1月8日）至藏历2050年十二月三十（公历2051年2月11日）
 *
 * @author 6tail
 */
class RabByungDay(
    year: Int,
    month: Int,
    day: Int
) : DayUnit(year, month, abs(day)) {

    /**
     * 是否闰日
     */
    private var leap: Boolean

    init {
        validate(year, month, day)
        leap = day < 0
    }

    /**
     * 藏历月
     *
     * @return 藏历月
     */
    fun getRabByungMonth(): RabByungMonth {
        return RabByungMonth.fromYm(year, month)
    }

    /**
     * 是否闰日
     *
     * @return true/false
     */
    fun isLeap(): Boolean {
        return leap
    }

    /**
     * 日
     *
     * @return 日，当日为闰日时，返回负数
     */
    fun getDayWithLeap(): Int {
        return if (leap) -day else day
    }

    override fun getName(): String {
        return (if (leap) "闰" else "") + NAMES[day - 1]
    }

    override fun toString(): String {
        return getRabByungMonth().toString() + getName()
    }

    /**
     * 藏历日相减
     *
     * @param target 藏历日
     * @return 相差天数
     */
    fun subtract(target: RabByungDay): Int {
        return getSolarDay().subtract(target.getSolarDay())
    }

    /**
     * 公历日
     *
     * @return 公历日
     */
    fun getSolarDay(): SolarDay {
        var m: RabByungMonth = RabByungMonth.fromYm(1950, 12)
        val cm: RabByungMonth = getRabByungMonth()
        var n = 0
        while (m != cm) {
            n += m.getDayCount()
            m = m.next(1)
        }
        var t = day
        for (d in m.getSpecialDays()) {
            if (d < 0) {
                if (t > -d) {
                    t--
                }
            } else if (d > 0) {
                if (t > d) {
                    t++
                }
            }
        }
        if (leap) {
            t++
        }
        return SolarDay(1951, 1, 7).next(n + t)
    }

    override fun next(n: Int): RabByungDay {
        return getSolarDay().next(n).getRabByungDay()
    }

    companion object {
        val NAMES: Array<String> = arrayOf("初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九", "初十", "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十", "廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七", "廿八", "廿九", "三十")

        @JvmStatic
        fun validate(year: Int, month: Int, day: Int) {
            require(day in -30 .. 30 && day != 0) { "illegal day $day in $month" }
            val leap: Boolean = day < 0
            var d: Int = day
            if (leap) {
                d = -d
            }
            val m: RabByungMonth = RabByungMonth.fromYm(year, month)
            require(!(leap && !m.getLeapDays().contains(d))) { "illegal leap day $d in $m" }
            require(!(!leap && m.getMissDays().contains(d))) { "illegal day $d in $m" }
        }

        /**
         * 从藏历年月日初始化
         *
         * @param year  藏历年
         * @param month 藏历月，闰月为负
         * @param day   藏历日，闰日为负
         */
        @JvmStatic
        fun fromYmd(year: Int, month: Int, day: Int): RabByungDay {
            return RabByungDay(year, month, day)
        }

        @JvmStatic
        fun fromSolarDay(solarDay: SolarDay): RabByungDay {
            var days: Int = solarDay.subtract(SolarDay(1951, 1, 8))
            var m: RabByungMonth = RabByungMonth.fromYm(1950, 12)
            var count: Int = m.getDayCount()
            while (days >= count) {
                days -= count
                m = m.next(1)
                count = m.getDayCount()
            }
            var day: Int = days + 1
            for (d in m.getSpecialDays()) {
                if (d < 0) {
                    if (day >= -d) {
                        day++
                    }
                } else if (d > 0) {
                    if (day == d + 1) {
                        day = -d
                        break
                    } else if (day > d + 1) {
                        day--
                    }
                }
            }
            return RabByungDay(m.year, m.getMonthWithLeap(), day)
        }
    }
}

package com.tyme.solar

import com.tyme.AbstractTyme
import com.tyme.rabbyung.RabByungYear

/**
 * 公历年
 *
 * @author 6tail
 */
class SolarYear(
    /** 年 */
    private var year: Int
) : AbstractTyme() {

    init {
        require(year in 1..9999) { "illegal solar year: $year" }
    }

    /**
     * 年
     *
     * @return 年
     */
    fun getYear(): Int {
        return year
    }

    /**
     * 天数（1582年355天，平年365天，闰年366天）
     *
     * @return 天数
     */
    fun getDayCount(): Int {
        if (1582 == year) {
            return 355
        }
        return if (isLeap()) 366 else 365
    }

    /**
     * 是否闰年(1582年以前，使用儒略历，能被4整除即为闰年。以后采用格里历，四年一闰，百年不闰，四百年再闰。)
     *
     * @return true/false
     */
    fun isLeap(): Boolean {
        if (year < 1600) {
            return year % 4 == 0
        }
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    }

    override fun getName(): String {
        return "${year}年"
    }

    override fun next(n: Int): SolarYear {
        return SolarYear(year + n)
    }

    /**
     * 月份列表
     *
     * @return 月份列表，1年有12个月。
     */
    fun getMonths(): List<SolarMonth> {
        val l: MutableList<SolarMonth> = ArrayList(12)
        for (i in 1..12) {
            l.add(SolarMonth(year, i))
        }
        return l
    }

    /**
     * 季度列表
     *
     * @return 季度列表，1年有4个季度。
     */
    fun getSeasons(): List<SolarSeason> {
        val l: MutableList<SolarSeason> = ArrayList(4)
        for (i in 0..3) {
            l.add(SolarSeason(year, i))
        }
        return l
    }

    /**
     * 半年列表
     *
     * @return 半年列表，1年有2个半年。
     */
    fun getHalfYears(): List<SolarHalfYear> {
        val l: MutableList<SolarHalfYear> = ArrayList(2)
        for (i in 0..1) {
            l.add(SolarHalfYear(year, i))
        }
        return l
    }

    /**
     * 藏历年
     *
     * @return 藏历年
     */
    fun getRabByungYear(): RabByungYear {
        return RabByungYear.fromYear(year)
    }

    override fun equals(other: Any?): Boolean {
        return other is SolarYear && toString() == other.toString()
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    companion object {
        /**
         * 从年初始化
         *
         * @param year  年，支持1到9999年
         * @return 公历年
         */
        @JvmStatic
        fun fromYear(year: Int): SolarYear {
            return SolarYear(year)
        }
    }
}

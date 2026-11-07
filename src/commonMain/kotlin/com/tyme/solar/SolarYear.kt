package com.tyme.solar

import com.tyme.rabbyung.RabByungYear
import com.tyme.unit.AbstractYear
import kotlin.jvm.JvmStatic

/**
 * 公历年
 *
 * @author 6tail
 */
class SolarYear(year: Int): AbstractYear(year) {

    init {
        validate(year)
    }

    /**
     * 天数（1582年355天，平年365天，闰年366天）
     */
    val dayCount: Int
        get() = getDayCount(year)

    /**
     * 是否闰年(1582年以前，使用儒略历，能被4整除即为闰年。以后采用格里历，四年一闰，百年不闰，四百年再闰。)
     *
     * @return true/false
     */
    fun isLeap(): Boolean {
        return isLeap(year)
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
        return buildMonths { i ->
            SolarMonth(year, i)
        }
    }

    /**
     * 季度列表
     *
     * @return 季度列表，1年有4个季度。
     */
    fun getSeasons(): List<SolarSeason> {
        return List(4) { i ->
            SolarSeason(year, i)
        }
    }

    /**
     * 半年列表
     *
     * @return 半年列表，1年有2个半年。
     */
    fun getHalfYears(): List<SolarHalfYear> {
        return List(2) { i ->
            SolarHalfYear(year, i)
        }
    }

    /**
     * 藏历年
     *
     * @return 藏历年
     */
    fun getRabByungYear(): RabByungYear {
        return RabByungYear(year)
    }

    companion object {
        @JvmStatic
        fun validate(year: Int) {
            validateRange(year, 1, 9999, "solar year")
        }

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

        /**
         * 天数（1582年355天，平年365天，闰年366天）
         *
         * @param year 公历年
         * @return 天数
         */
        @JvmStatic
        fun getDayCount(year: Int): Int {
            if (1582 == year) {
                return 355
            }
            return if (isLeap(year)) 366 else 365
        }

        /**
         * 是否闰年(1582年以前，使用儒略历，能被4整除即为闰年。以后采用格里历，四年一闰，百年不闰，四百年再闰。)
         *
         * @param year 公历年
         * @return true/false
         */
        fun isLeap(year: Int): Boolean {
            if (year < 1600) {
                return year % 4 == 0
            }
            return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
        }
    }
}

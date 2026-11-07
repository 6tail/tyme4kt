package com.tyme.solar

import com.tyme.unit.YearUnit
import kotlin.jvm.JvmStatic

/**
 * 公历半年
 *
 * @author 6tail
 */
class SolarHalfYear(year: Int, val index: Int): YearUnit(year) {

    init {
        validate(year, index)
    }

    /**
     * 公历年
     */
    val solarYear: SolarYear
        get() = SolarYear(year)

    override fun getName(): String {
        return NAMES[index]
    }

    override fun toString(): String {
        return "${solarYear}${getName()}"
    }

    override fun next(n: Int): SolarHalfYear {
        val i: Int = index + n
        return SolarHalfYear((year * 2 + i) / 2, indexOf(i, 2))
    }

    /**
     * 月份列表
     *
     * @return 月份列表，半年有6个月。
     */
    fun getMonths(): List<SolarMonth> {
        return List(6) { i ->
            SolarMonth(year, index * 6 + i + 1)
        }
    }

    /**
     * 季度列表
     *
     * @return 季度列表，半年有2个季度。
     */
    fun getSeasons(): List<SolarSeason> {
        return List(2) { i ->
            SolarSeason(year, index * 2 + i)
        }
    }

    companion object {
        val NAMES: Array<String> = arrayOf("上半年", "下半年")

        @JvmStatic
        fun validate(year: Int, index: Int) {
            validateRange(index, 0, 1, "solar half year index")
            SolarYear.validate(year)
        }

        @JvmStatic
        fun fromIndex(year: Int, index: Int): SolarHalfYear {
            return SolarHalfYear(year, index)
        }
    }
}

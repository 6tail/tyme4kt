package com.tyme.solar

import com.tyme.unit.YearUnit
import kotlin.jvm.JvmStatic

/**
 * 公历半年
 *
 * @author 6tail
 */
class SolarHalfYear(
    year: Int,
    val index: Int
) : YearUnit(year) {

    init {
        validate(year, index)
    }

    /**
     * 公历年
     *
     * @return 公历年
     */
    fun getSolarYear(): SolarYear {
        return SolarYear.fromYear(year)
    }

    override fun getName(): String {
        return NAMES[index]
    }

    override fun toString(): String {
        return "${getSolarYear()}${getName()}"
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
        val l: MutableList<SolarMonth> = ArrayList(6)
        for (i in 1..6) {
            l.add(SolarMonth(year, index * 6 + i))
        }
        return l
    }

    /**
     * 季度列表
     *
     * @return 季度列表，半年有2个季度。
     */
    fun getSeasons(): List<SolarSeason> {
        val l: MutableList<SolarSeason> = ArrayList(2)
        for (i in 0..1) {
            l.add(SolarSeason(year, index * 2 + i))
        }
        return l
    }

    override fun equals(other: Any?): Boolean {
        return other is SolarHalfYear && toString() == other.toString()
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    companion object {
        val NAMES: Array<String> = arrayOf("上半年", "下半年")

        @JvmStatic
        fun validate(year: Int, index: Int) {
            if (index !in 0..1) {
                throw IllegalArgumentException("illegal solar half year index: $index")
            }
            SolarYear.validate(year)
        }

        @JvmStatic
        fun fromIndex(year: Int, index: Int): SolarHalfYear {
            return SolarHalfYear(year, index)
        }
    }
}

package com.tyme.solar

import com.tyme.unit.YearUnit
import kotlin.jvm.JvmStatic

/**
 * 公历季度
 *
 * @author 6tail
 */
class SolarSeason(
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

    override fun next(n: Int): SolarSeason {
        val i: Int = index + n
        return SolarSeason((year * 4 + i) / 4, indexOf(i, 4))
    }

    /**
     * 月份列表
     *
     * @return 月份列表，1季度有3个月。
     */
    fun getMonths(): List<SolarMonth> {
        val l: MutableList<SolarMonth> = ArrayList(3)
        for (i in 1..3) {
            l.add(SolarMonth(year, index * 3 + i))
        }
        return l
    }

    override fun equals(other: Any?): Boolean {
        return other is SolarSeason && toString() == other.toString()
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    companion object {
        val NAMES: Array<String> = arrayOf("一季度", "二季度", "三季度", "四季度")

        @JvmStatic
        fun validate(year: Int, index: Int) {
            if (index !in 0..3) {
                throw IllegalArgumentException("illegal solar season index: $index")
            }
            SolarYear.validate(year)
        }

        @JvmStatic
        fun fromIndex(year: Int, index: Int): SolarSeason {
            return SolarSeason(year, index)
        }
    }
}

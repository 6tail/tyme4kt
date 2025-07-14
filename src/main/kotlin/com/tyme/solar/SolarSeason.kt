package com.tyme.solar

import com.tyme.AbstractTyme

/**
 * 公历季度
 *
 * @author 6tail
 */
class SolarSeason(
    /** 年 */
    year: Int,
    /** 索引，0-3 */
    private var index: Int
) : AbstractTyme() {

    /** 年 */
    private var year: SolarYear

    init {
        require(index in 0..3) { "illegal solar season index: $index" }
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
     * 索引
     *
     * @return 索引，0-3
     */
    fun getIndex(): Int {
        return index
    }

    override fun getName(): String {
        return NAMES[index]
    }

    override fun toString(): String {
        return "${year}${getName()}"
    }

    override fun next(n: Int): SolarSeason {
        val i = index + n
        return SolarSeason((getYear() * 4 + i) / 4, indexOf(i, 4))
    }

    /**
     * 月份列表
     *
     * @return 月份列表，1季度有3个月。
     */
    fun getMonths(): List<SolarMonth> {
        val l: MutableList<SolarMonth> = ArrayList(3)
        val y = year.getYear()
        for (i in 1..3) {
            l.add(SolarMonth(y, index * 3 + i))
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
        fun fromIndex(year: Int, index: Int): SolarSeason {
            return SolarSeason(year, index)
        }
    }
}

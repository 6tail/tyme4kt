package com.tyme.solar

import com.tyme.AbstractTyme
import kotlin.jvm.JvmStatic

/**
 * 公历半年
 *
 * @author 6tail
 */
class SolarHalfYear
/**
 * 初始化
 *
 * @param year  年
 * @param index 索引，0-1
 */(
    year: Int,
    /** 索引，0-1 */
    private var index: Int
) : AbstractTyme() {

    /** 年 */
    private var year: SolarYear

    init {
        require(index in 0..1) { "illegal solar half year index: $index" }
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
     * @return 索引，0-1
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

    override fun next(n: Int): SolarHalfYear {
        val i = index + n
        return SolarHalfYear((getYear() * 2 + i) / 2, indexOf(i, 2))
    }

    /**
     * 月份列表
     *
     * @return 月份列表，半年有6个月。
     */
    fun getMonths(): List<SolarMonth> {
        val l: MutableList<SolarMonth> = ArrayList(6)
        val y = year.getYear()
        for (i in 1..6) {
            l.add(SolarMonth(y, index * 6 + i))
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
        val y = year.getYear()
        for (i in 0..1) {
            l.add(SolarSeason(y, index * 2 + i))
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
        fun fromIndex(year: Int, index: Int): SolarHalfYear {
            return SolarHalfYear(year, index)
        }
    }
}

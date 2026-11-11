package com.tyme.sixtycycle

import com.tyme.AbstractTyme
import com.tyme.culture.Direction
import com.tyme.culture.star.nine.NineStar
import com.tyme.solar.SolarTerm
import kotlin.jvm.JvmStatic

/**
 * 干支月
 *
 * @author 6tail
 */
class SixtyCycleMonth: AbstractTyme {
    /**
     * 干支年
     */
    val sixtyCycleYear: SixtyCycleYear

    /**
     * 年柱
     */
    val year: SixtyCycle
        get() = sixtyCycleYear.getSixtyCycle()

    /**
     * 月柱
     */
    private var month: SixtyCycle

    /**
     * 位于当年的索引(0-11)，寅月为0，依次类推
     */
    val indexInYear: Int
        get() = month.getEarthBranch().next(-2).index

    constructor(year: SixtyCycleYear, month: SixtyCycle): super() {
        this.sixtyCycleYear = year
        this.month = month
    }

    /**
     * 干支
     *
     * @return 干支
     */
    fun getSixtyCycle(): SixtyCycle {
        return month
    }

    override fun getName(): String {
        return "${month.getName()}月"
    }

    override fun toString(): String {
        return sixtyCycleYear.toString() + getName()
    }

    override fun next(n: Int): SixtyCycleMonth {
        return SixtyCycleMonth(SixtyCycleYear((sixtyCycleYear.year * 12 + indexInYear + n) / 12), month.next(n))
    }

    /**
     * 九星
     *
     * @return 九星
     */
    fun getNineStar(): NineStar {
        return NineStar.fromYm(sixtyCycleYear, month)
    }

    /**
     * 太岁方位
     *
     * @return 方位
     */
    fun getJupiterDirection(): Direction {
        return Direction.monthJupiter(month)
    }

    /**
     * 首日（节令当天）
     */
    val firstDay: SixtyCycleDay
        get() = SixtyCycleDay(SolarTerm(sixtyCycleYear.year, 3 + indexInYear * 2).getSolarDay())

    /**
     * 本月的干支日列表
     *
     * @return 干支日列表
     */
    fun getDays(): List<SixtyCycleDay> {
        val l: MutableList<SixtyCycleDay> = ArrayList()
        var d: SixtyCycleDay = firstDay
        while (d.sixtyCycleMonth == this) {
            l.add(d)
            d = d.next(1)
        }
        return l
    }

    companion object {
        /**
         * 从年和月索引初始化
         *
         * @param year  年
         * @param index 月索引
         * @return 干支月
         */
        @JvmStatic
        fun fromIndex(year: Int, index: Int): SixtyCycleMonth {
            return SixtyCycleYear(year).firstMonth.next(index)
        }
    }
}
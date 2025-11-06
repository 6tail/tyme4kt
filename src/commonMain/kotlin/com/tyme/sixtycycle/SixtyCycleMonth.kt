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
class SixtyCycleMonth(
    /** 干支年 */
    protected var year: SixtyCycleYear,
    /** 月柱 */
    protected var month: SixtyCycle
) : AbstractTyme() {

    /**
     * 干支年
     *
     * @return 干支年
     */
    fun getSixtyCycleYear(): SixtyCycleYear {
        return year
    }

    /**
     * 年柱
     *
     * @return 年柱
     */
    fun getYear(): SixtyCycle {
        return year.getSixtyCycle()
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
        return year.toString() + getName()
    }

    override fun next(n: Int): SixtyCycleMonth {
        return SixtyCycleMonth(SixtyCycleYear((year.getYear() * 12 + getIndexInYear() + n) / 12), month.next(n))
    }

    /**
     * 位于当年的索引(0-11)，寅月为0，依次类推
     *
     * @return 索引
     */
    fun getIndexInYear(): Int {
        return month.getEarthBranch().next(-2).getIndex()
    }

    /**
     * 九星
     *
     * @return 九星
     */
    fun getNineStar(): NineStar {
        var index: Int = getSixtyCycle().getEarthBranch().getIndex()
        if (index < 2) {
            index += 3
        }
        return NineStar(27 - year.getSixtyCycle().getEarthBranch().getIndex() % 3 * 3 - index)
    }

    /**
     * 太岁方位
     *
     * @return 方位
     */
    fun getJupiterDirection(): Direction {
        val sixtyCycle: SixtyCycle = getSixtyCycle()
        val n: Int = intArrayOf(7, -1, 1, 3)[sixtyCycle.getEarthBranch().next(-2).getIndex() % 4]
        return if(n == -1) sixtyCycle.getHeavenStem().getDirection() else Direction(n)
    }

    /**
     * 首日（节令当天）
     *
     * @return 干支日
     */
    fun getFirstDay(): SixtyCycleDay {
        return SixtyCycleDay(SolarTerm(year.getYear(), 3 + getIndexInYear() * 2).getSolarDay())
    }

    /**
     * 本月的干支日列表
     *
     * @return 干支日列表
     */
    fun getDays(): List<SixtyCycleDay> {
        val l: MutableList<SixtyCycleDay> = mutableListOf()
        var d: SixtyCycleDay = getFirstDay()
        while (d.getSixtyCycleMonth() == this) {
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
            return SixtyCycleYear(year).getFirstMonth().next(index)
        }
    }
}
package com.tyme.sixtycycle

import com.tyme.AbstractTyme
import com.tyme.culture.Direction
import com.tyme.culture.Twenty
import com.tyme.culture.star.nine.NineStar
import kotlin.jvm.JvmStatic
import kotlin.math.floor

/**
 * 干支年
 *
 * @author 6tail
 */
class SixtyCycleYear(
    /** 年，支持-1到9999年 */
    private var year: Int
) : AbstractTyme() {

    init {
        require(year in -1 .. 9999) { "illegal sixty cycle year: $year" }
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
     * 干支
     *
     * @return 干支
     */
    fun getSixtyCycle(): SixtyCycle {
        return SixtyCycle(year - 4)
    }

    override fun getName(): String {
        return "${getSixtyCycle().getName()}年"
    }

    /**
     * 运
     *
     * @return 运
     */
    fun getTwenty(): Twenty {
        return Twenty(floor((year - 1864) / 20.0).toInt())
    }

    /**
     * 九星
     *
     * @return 九星
     */
    fun getNineStar(): NineStar {
        return NineStar(63 + getTwenty().getSixty().getIndex() * 3 - getSixtyCycle().getIndex())
    }

    /**
     * 太岁方位
     *
     * @return 方位
     */
    fun getJupiterDirection(): Direction {
        return Direction(intArrayOf(0, 7, 7, 2, 3, 3, 8, 1, 1, 6, 0, 0)[getSixtyCycle().getEarthBranch().getIndex()])
    }

    /**
     * 推移
     *
     * @param n 推移年数
     * @return 干支年
     */
    override fun next(n: Int): SixtyCycleYear {
        return SixtyCycleYear(year + n)
    }

    /**
     * 首月（五虎遁：甲己之年丙作首，乙庚之岁戊为头，丙辛必定寻庚起，丁壬壬位顺行流，若问戊癸何方发，甲寅之上好追求。）
     *
     * @return 干支月
     */
    fun getFirstMonth(): SixtyCycleMonth {
        return SixtyCycleMonth(this, SixtyCycle(HeavenStem((getSixtyCycle().getHeavenStem().getIndex() + 1) * 2).getName() + "寅"))
    }

    /**
     * 干支月列表
     *
     * @return 干支月列表
     */
    fun getMonths(): List<SixtyCycleMonth> {
        val l: MutableList<SixtyCycleMonth> = mutableListOf()
        val m: SixtyCycleMonth = getFirstMonth()
        l.add(m)
        for (i in 1 until 12) {
            l.add(m.next(i))
        }
        return l
    }

    companion object {
        /**
         * 从年初始化
         *
         * @param year  年，支持-1到9999年
         * @return 干支年
         */
        @JvmStatic
        fun fromYear(year: Int): SixtyCycleYear {
            return SixtyCycleYear(year)
        }
    }
}

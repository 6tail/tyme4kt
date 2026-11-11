package com.tyme.unit

import com.tyme.culture.Direction
import com.tyme.culture.Twenty
import com.tyme.culture.star.nine.NineStar
import com.tyme.sixtycycle.SixtyCycle
import kotlin.math.floor

/**
 * 传统年抽象
 *
 * @author 6tail
 */
abstract class AbstractTraditionalYear(year: Int): AbstractYear(year) {
    /**
     * 干支
     *
     * @return 干支
     */
    fun getSixtyCycle(): SixtyCycle {
        return SixtyCycle(year - 4)
    }

    /**
     * 运
     */
    val twenty: Twenty
        get() = Twenty(floor((year - 1864) / 20.0).toInt())

    /**
     * 九星
     *
     * @return 九星
     */
    fun getNineStar(): NineStar {
        return NineStar(63 + twenty.getSixty().index * 3 - getSixtyCycle().index)
    }

    /**
     * 太岁方位
     *
     * @return 方位
     */
    fun getJupiterDirection(): Direction {
        return Direction(intArrayOf(0, 7, 7, 2, 3, 3, 8, 1, 1, 6, 0, 0)[getSixtyCycle().getEarthBranch().index])
    }
}

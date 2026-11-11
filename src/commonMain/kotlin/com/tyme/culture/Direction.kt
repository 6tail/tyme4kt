package com.tyme.culture

import com.tyme.LoopTyme
import com.tyme.sixtycycle.SixtyCycle
import kotlin.jvm.JvmStatic

/**
 * 方位
 *
 * @author 6tail
 */
class Direction: LoopTyme {
    constructor(index: Int): super(NAMES, index)

    constructor(name: String): super(NAMES, name)

    override fun next(n: Int): Direction {
        return Direction(nextIndex(n))
    }

    /**
     * 九野
     *
     * @return 九野
     */
    fun getLand(): Land {
        return Land(index)
    }

    /**
     * 五行
     *
     * @return 五行
     */
    fun getElement(): Element {
        return Element(intArrayOf(4, 2, 0, 0, 2, 3, 3, 2, 1)[index])
    }

    companion object {
        /**
         * 依据后天八卦排序（0坎北, 1坤西南, 2震东, 3巽东南, 4中, 5乾西北, 6兑西, 7艮东北, 8离南）
         */
        val NAMES: Array<String> = arrayOf("北", "西南", "东", "东南", "中", "西北", "西", "东北", "南")

        /**
         * 月太岁方位
         *
         * @param month 月干支
         * @return 太岁方位
         */
        @JvmStatic
        fun monthJupiter(month: SixtyCycle): Direction {
            val n = intArrayOf(7, -1, 1, 3)[month.getEarthBranch().next(-2).index % 4]
            return if (n == -1) month.getHeavenStem().getDirection() else Direction(n)
        }

        /**
         * 日太岁方位
         *
         * @param yearJupiter 年太岁方位
         * @param day 日干支
         * @return 太岁方位
         */
        @JvmStatic
        fun dayJupiter(yearJupiter: Direction, day: SixtyCycle): Direction {
            val index: Int = day.index
            return if (index % 12 < 6) Element(index / 12).getDirection() else yearJupiter
        }

        @JvmStatic
        fun fromIndex(index: Int): Direction {
            return Direction(index)
        }

        @JvmStatic
        fun fromName(name: String): Direction {
            return Direction(name)
        }
    }
}

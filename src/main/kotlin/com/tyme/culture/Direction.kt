package com.tyme.culture

import com.tyme.LoopTyme

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

    /** 九野 */
    fun getLand(): Land {
        return Land(this.getIndex())
    }

    /** 五行 */
    fun getElement(): Element {
        return Element(intArrayOf(4, 2, 0, 0, 2, 3, 3, 2, 1)[this.getIndex()])
    }

    companion object {
        /** 依据后天八卦排序（0坎北, 1坤西南, 2震东, 3巽东南, 4中, 5乾西北, 6兑西, 7艮东北, 8离南） */
        val NAMES: Array<String> = arrayOf("北", "西南", "东", "东南", "中", "西北", "西", "东北", "南")

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

package com.tyme.culture

import com.tyme.LoopTyme
import kotlin.jvm.JvmStatic

/**
 * 宫
 *
 * @author 6tail
 */
class Zone: LoopTyme {
    constructor(index: Int): super(NAMES, index)

    constructor(name: String): super(NAMES, name)

    override fun next(n: Int): Zone {
        return Zone(nextIndex(n))
    }

    /** 方位 */
    fun getDirection(): Direction {
        return Direction(this.getName())
    }

    /** 神兽 */
    fun getBeast(): Beast {
        return Beast(getIndex())
    }

    companion object {
        val NAMES: Array<String> = arrayOf("东", "北", "西", "南")

        @JvmStatic
        fun fromIndex(index: Int): Zone {
            return Zone(index)
        }

        @JvmStatic
        fun fromName(name: String): Zone {
            return Zone(name)
        }
    }
}

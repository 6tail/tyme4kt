package com.tyme.culture

import com.tyme.LoopTyme
import kotlin.jvm.JvmStatic

/**
 * 九野
 *
 * @author 6tail
 */
class Land: LoopTyme {
    constructor(index: Int): super(NAMES, index)

    constructor(name: String): super(NAMES, name)

    override fun next(n: Int): Land {
        return Land(nextIndex(n))
    }

    /** 方位 */
    fun getDirection(): Direction {
        return Direction(getIndex())
    }

    companion object {
        val NAMES: Array<String> = arrayOf("玄天", "朱天", "苍天", "阳天", "钧天", "幽天", "颢天", "变天", "炎天")

        @JvmStatic
        fun fromIndex(index: Int): Land {
            return Land(index)
        }

        @JvmStatic
        fun fromName(name: String): Land {
            return Land(name)
        }
    }
}

package com.tyme.culture

import com.tyme.LoopTyme

/**
 * 神兽
 *
 * @author 6tail
 */
class Beast: LoopTyme {
    constructor(index: Int): super(NAMES, index)

    constructor(name: String): super(NAMES, name)

    override fun next(n: Int): Beast {
        return Beast(nextIndex(n))
    }

    /** 宫 */
    fun getZone(): Zone {
        return Zone(this.getIndex())
    }

    companion object {
        val NAMES: Array<String> = arrayOf("青龙", "玄武", "白虎", "朱雀")

        @JvmStatic
        fun fromIndex(index: Int): Beast {
            return Beast(index)
        }

        @JvmStatic
        fun fromName(name: String): Beast {
            return Beast(name)
        }
    }
}

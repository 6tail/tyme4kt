package com.tyme.culture

import com.tyme.LoopTyme

/**
 * 吉凶
 *
 * @author 6tail
 */
class Luck: LoopTyme {
    constructor(index: Int): super(NAMES, index)

    constructor(name: String): super(NAMES, name)

    override fun next(n: Int): Luck {
        return Luck(nextIndex(n))
    }

    companion object {
        val NAMES: Array<String> = arrayOf("吉", "凶")

        @JvmStatic
        fun fromIndex(index: Int): Luck {
            return Luck(index)
        }

        @JvmStatic
        fun fromName(name: String): Luck {
            return Luck(name)
        }
    }
}

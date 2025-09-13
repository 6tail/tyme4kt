package com.tyme.culture

import com.tyme.LoopTyme
import kotlin.jvm.JvmStatic

/**
 * 旬
 *
 * @author 6tail
 */
class Ten: LoopTyme {
    constructor(index: Int): super(NAMES, index)

    constructor(name: String): super(NAMES, name)

    override fun next(n: Int): Ten {
        return Ten(nextIndex(n))
    }

    companion object {
        val NAMES: Array<String> = arrayOf("甲子", "甲戌", "甲申", "甲午", "甲辰", "甲寅")

        @JvmStatic
        fun fromIndex(index: Int): Ten {
            return Ten(index)
        }

        @JvmStatic
        fun fromName(name: String): Ten {
            return Ten(name)
        }
    }
}

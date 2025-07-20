package com.tyme.culture

import com.tyme.LoopTyme
import kotlin.jvm.JvmStatic

/**
 * 元（60年=1元）
 *
 * @author 6tail
 */
class Sixty: LoopTyme {
    constructor(index: Int): super(NAMES, index)

    constructor(name: String): super(NAMES, name)

    override fun next(n: Int): Sixty {
        return Sixty(nextIndex(n))
    }

    companion object {
        val NAMES: Array<String> = arrayOf("上元", "中元", "下元")

        @JvmStatic
        fun fromIndex(index: Int): Sixty {
            return Sixty(index)
        }

        @JvmStatic
        fun fromName(name: String): Sixty {
            return Sixty(name)
        }
    }
}

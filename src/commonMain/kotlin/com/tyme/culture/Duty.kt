package com.tyme.culture

import com.tyme.LoopTyme
import kotlin.jvm.JvmStatic

/**
 * 建除十二值神
 *
 * @author 6tail
 */
class Duty: LoopTyme {
    constructor(index: Int): super(NAMES, index)

    constructor(name: String): super(NAMES, name)

    override fun next(n: Int): Duty {
        return Duty(nextIndex(n))
    }

    companion object {
        val NAMES: Array<String> = arrayOf("建", "除", "满", "平", "定", "执", "破", "危", "成", "收", "开", "闭")

        @JvmStatic
        fun fromIndex(index: Int): Duty {
            return Duty(index)
        }

        @JvmStatic
        fun fromName(name: String): Duty {
            return Duty(name)
        }
    }
}

package com.tyme.culture

import com.tyme.LoopTyme
import kotlin.jvm.JvmStatic

/**
 * 运（20年=1运，3运=1元）
 *
 * @author 6tail
 */
class Twenty: LoopTyme {
    constructor(index: Int): super(NAMES, index)

    constructor(name: String): super(NAMES, name)

    override fun next(n: Int): Twenty {
        return Twenty(nextIndex(n))
    }

    /**
     * 元
     *
     * @return 元
     */
    fun getSixty(): Sixty {
        return Sixty(getIndex() / 3)
    }

    companion object {
        val NAMES: Array<String> = arrayOf("一运", "二运", "三运", "四运", "五运", "六运", "七运", "八运", "九运")

        @JvmStatic
        fun fromIndex(index: Int): Twenty {
            return Twenty(index)
        }

        @JvmStatic
        fun fromName(name: String): Twenty {
            return Twenty(name)
        }
    }
}

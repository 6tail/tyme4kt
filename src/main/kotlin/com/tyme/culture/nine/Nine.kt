package com.tyme.culture.nine

import com.tyme.LoopTyme

/**
 * 数九
 *
 * @author 6tail
 */
class Nine: LoopTyme {
    constructor(name: String): super(NAMES, name)

    constructor(index: Int): super(NAMES, index)

    override fun next(n: Int): Nine {
        return Nine(nextIndex(n))
    }

    companion object {
        val NAMES: Array<String> = arrayOf("一九", "二九", "三九", "四九", "五九", "六九", "七九", "八九", "九九")

        @JvmStatic
        fun fromIndex(index: Int): Nine {
            return Nine(index)
        }

        @JvmStatic
        fun fromName(name: String): Nine {
            return Nine(name)
        }
    }
}

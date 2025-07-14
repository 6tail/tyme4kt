package com.tyme.culture.plumrain

import com.tyme.LoopTyme

/**
 * 梅雨
 *
 * @author 6tail
 */
class PlumRain : LoopTyme {
    constructor(name: String) : super(NAMES, name)

    constructor(index: Int) : super(NAMES, index)

    override fun next(n: Int): PlumRain {
        return PlumRain(nextIndex(n))
    }

    companion object {
        val NAMES: Array<String> = arrayOf("入梅", "出梅")

        @JvmStatic
        fun fromIndex(index: Int): PlumRain {
            return PlumRain(index)
        }

        @JvmStatic
        fun fromName(name: String): PlumRain {
            return PlumRain(name)
        }
    }
}

package com.tyme.culture.plumrain

import com.tyme.LoopTyme
import kotlin.jvm.JvmStatic

/**
 * 梅雨
 *
 * @author 6tail
 */
class PlumRain: LoopTyme {
    constructor(name: String): super(NAMES, name)

    constructor(index: Int): super(NAMES, index)

    override fun next(n: Int): PlumRain {
        return PlumRain(nextIndex(n))
    }

    companion object {
        val NAMES: Array<String> = arrayOf("入梅", "出梅")

        /**
         * 从索引初始化
         *
         * @param index 索引
         * @return 梅雨
         */
        @JvmStatic
        fun fromIndex(index: Int): PlumRain {
            return PlumRain(index)
        }

        /**
         * 从名称初始化
         *
         * @param name 名称
         * @return 梅雨
         */
        @JvmStatic
        fun fromName(name: String): PlumRain {
            return PlumRain(name)
        }
    }
}

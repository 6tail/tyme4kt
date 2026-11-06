package com.tyme.culture.dog

import com.tyme.LoopTyme
import kotlin.jvm.JvmStatic

/**
 * 三伏
 *
 * @author 6tail
 */
class Dog: LoopTyme {
    constructor(name: String): super(NAMES, name)

    constructor(index: Int): super(NAMES, index)

    override fun next(n: Int): Dog {
        return Dog(nextIndex(n))
    }

    companion object {
        val NAMES: Array<String> = arrayOf("初伏", "中伏", "末伏")

        /**
         * 从索引初始化
         *
         * @param index 索引
         * @return 伏
         */
        @JvmStatic
        fun fromIndex(index: Int): Dog {
            return Dog(index)
        }

        /**
         * 从名称初始化
         *
         * @param name 名称
         * @return 伏
         */
        @JvmStatic
        fun fromName(name: String): Dog {
            return Dog(name)
        }
    }
}

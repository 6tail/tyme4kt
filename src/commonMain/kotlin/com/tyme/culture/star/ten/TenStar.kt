package com.tyme.culture.star.ten

import com.tyme.LoopTyme
import kotlin.jvm.JvmStatic

/**
 * 十神
 *
 * @author 6tail
 */
class TenStar : LoopTyme {
    constructor(index: Int) : super(NAMES, index)

    constructor(name: String) : super(NAMES, name)

    override fun next(n: Int): TenStar {
        return TenStar(nextIndex(n))
    }

    companion object {
        val NAMES: Array<String> =
            arrayOf("比肩", "劫财", "食神", "伤官", "偏财", "正财", "七杀", "正官", "偏印", "正印")

        @JvmStatic
        fun fromIndex(index: Int): TenStar {
            return TenStar(index)
        }

        @JvmStatic
        fun fromName(name: String): TenStar {
            return TenStar(name)
        }
    }
}

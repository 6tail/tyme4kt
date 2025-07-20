package com.tyme.culture.star.twelve

import com.tyme.LoopTyme
import kotlin.jvm.JvmStatic

/**
 * 黄道黑道十二神
 *
 * @author 6tail
 */
class TwelveStar : LoopTyme {
    constructor(index: Int) : super(NAMES, index)

    constructor(name: String) : super(NAMES, name)

    override fun next(n: Int): TwelveStar {
        return TwelveStar(nextIndex(n))
    }

    /**
     * 黄道黑道
     *
     * @return 黄道黑道
     */
    fun getEcliptic(): Ecliptic {
        return Ecliptic(intArrayOf(0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1)[getIndex()])
    }

    companion object {
        val NAMES: Array<String> =
            arrayOf("青龙", "明堂", "天刑", "朱雀", "金匮", "天德", "白虎", "玉堂", "天牢", "玄武", "司命", "勾陈")

        @JvmStatic
        fun fromIndex(index: Int): TwelveStar {
            return TwelveStar(index)
        }

        @JvmStatic
        fun fromName(name: String): TwelveStar {
            return TwelveStar(name)
        }
    }
}

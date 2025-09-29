package com.tyme.culture

import com.tyme.LoopTyme
import com.tyme.culture.star.seven.SevenStar
import kotlin.jvm.JvmStatic

/**
 * 星期
 *
 * @author 6tail
 */
class Week: LoopTyme {
    constructor(index: Int): super(NAMES, index)

    constructor(name: String): super(NAMES, name)

    override fun next(n: Int): Week {
        return Week(nextIndex(n))
    }

    /** 七曜 */
    fun getSevenStar(): SevenStar {
        return SevenStar(getIndex())
    }

    companion object {
        val NAMES: Array<String> = arrayOf("日", "一", "二", "三", "四", "五", "六")

        @JvmStatic
        fun fromIndex(index: Int): Week {
            return Week(index)
        }

        @JvmStatic
        fun fromName(name: String): Week {
            return Week(name)
        }
    }
}

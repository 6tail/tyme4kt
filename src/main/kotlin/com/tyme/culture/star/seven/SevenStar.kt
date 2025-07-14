package com.tyme.culture.star.seven

import com.tyme.LoopTyme
import com.tyme.culture.Week

/**
 * 七曜（七政、七纬、七耀）
 *
 * @author 6tail
 */
class SevenStar : LoopTyme {
    constructor(index: Int) : super(NAMES, index)

    constructor(name: String) : super(NAMES, name)

    override fun next(n: Int): SevenStar {
        return SevenStar(nextIndex(n))
    }

    /** 星期 */
    fun getWeek(): Week {
        return Week(this.getIndex())
    }

    companion object {
        val NAMES: Array<String> = arrayOf("日", "月", "火", "水", "木", "金", "土")

        @JvmStatic
        fun fromIndex(index: Int): SevenStar {
            return SevenStar(index)
        }

        @JvmStatic
        fun fromName(name: String): SevenStar {
            return SevenStar(name)
        }
    }
}

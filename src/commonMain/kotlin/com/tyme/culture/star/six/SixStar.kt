package com.tyme.culture.star.six

import com.tyme.LoopTyme
import kotlin.jvm.JvmStatic

/**
 * 六曜（孔明六曜星）
 *
 * @author 6tail
 */
class SixStar : LoopTyme {
    constructor(index: Int) : super(NAMES, index)

    constructor(name: String) : super(NAMES, name)

    override fun next(n: Int): SixStar {
        return SixStar(nextIndex(n))
    }

    companion object {
        val NAMES: Array<String> = arrayOf("先胜", "友引", "先负", "佛灭", "大安", "赤口")

        @JvmStatic
        fun fromIndex(index: Int): SixStar {
            return SixStar(index)
        }

        @JvmStatic
        fun fromName(name: String): SixStar {
            return SixStar(name)
        }
    }
}

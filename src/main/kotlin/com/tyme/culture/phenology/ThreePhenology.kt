package com.tyme.culture.phenology

import com.tyme.LoopTyme

/**
 * 三候
 *
 * @author 6tail
 */
class ThreePhenology: LoopTyme {
    constructor(name: String): super(NAMES, name)

    constructor(index: Int): super(NAMES, index)

    override fun next(n: Int): ThreePhenology {
        return ThreePhenology(nextIndex(n))
    }

    fun getThreePhenology(): ThreePhenology {
        return ThreePhenology(this.getIndex() % 3)
    }

    companion object {
        val NAMES: Array<String> = arrayOf("初候", "二候", "三候")

        @JvmStatic
        fun fromIndex(index: Int): ThreePhenology {
            return ThreePhenology(index)
        }

        @JvmStatic
        fun fromName(name: String): ThreePhenology {
            return ThreePhenology(name)
        }
    }
}

package com.tyme.lunar

import com.tyme.LoopTyme

/**
 * 农历季节
 *
 * @author 6tail
 */
class LunarSeason : LoopTyme {
    constructor(index: Int): super(NAMES, index)

    constructor(name: String): super(NAMES, name)

    override fun next(n: Int): LunarSeason {
        return LunarSeason(nextIndex(n))
    }

    override fun equals(other: Any?): Boolean {
        return other is LunarSeason && this.toString() == other.toString()
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    companion object {
        val NAMES: Array<String> = arrayOf("孟春", "仲春", "季春", "孟夏", "仲夏", "季夏", "孟秋", "仲秋", "季秋", "孟冬", "仲冬", "季冬")

        @JvmStatic
        fun fromIndex(index: Int): LunarSeason {
            return LunarSeason(index)
        }

        @JvmStatic
        fun fromName(name: String): LunarSeason {
            return LunarSeason(name)
        }
    }
}

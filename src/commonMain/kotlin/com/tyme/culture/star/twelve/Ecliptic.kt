package com.tyme.culture.star.twelve

import com.tyme.LoopTyme
import com.tyme.culture.Luck
import kotlin.jvm.JvmStatic

/**
 * 黄道黑道
 *
 * @author 6tail
 */
class Ecliptic : LoopTyme {
    constructor(index: Int) : super(NAMES, index)

    constructor(name: String) : super(NAMES, name)

    override fun next(n: Int): Ecliptic {
        return Ecliptic(nextIndex(n))
    }

    /**
     * 吉凶
     *
     * @return 吉凶
     */
    fun getLuck(): Luck {
        return Luck(this.getIndex())
    }

    companion object {
        val NAMES: Array<String> = arrayOf("黄道", "黑道")

        @JvmStatic
        fun fromIndex(index: Int): Ecliptic {
            return Ecliptic(index)
        }

        @JvmStatic
        fun fromName(name: String): Ecliptic {
            return Ecliptic(name)
        }
    }
}

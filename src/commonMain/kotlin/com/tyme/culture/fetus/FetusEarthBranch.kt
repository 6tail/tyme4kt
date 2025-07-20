package com.tyme.culture.fetus

import com.tyme.LoopTyme
import kotlin.jvm.JvmStatic

/**
 * 地支六甲胎神（《地支六甲胎神歌》子午二日碓须忌，丑未厕道莫修移。寅申火炉休要动，卯酉大门修当避。辰戌鸡栖巳亥床，犯着六甲身堕胎。）
 *
 * @author 6tail
 */
class FetusEarthBranch: LoopTyme {

    constructor(name: String): super(NAMES, name)

    constructor(index: Int): super(NAMES, index)

    override fun next(n: Int): FetusEarthBranch {
        return FetusEarthBranch(nextIndex(n))
    }

    companion object {
        val NAMES: Array<String> = arrayOf("碓", "厕", "炉", "门", "栖", "床")

        @JvmStatic
        fun fromIndex(index: Int): FetusEarthBranch {
            return FetusEarthBranch(index)
        }

        @JvmStatic
        fun fromName(name: String): FetusEarthBranch {
            return FetusEarthBranch(name)
        }
    }
}

package com.tyme.culture.fetus

import com.tyme.LoopTyme

/**
 * 天干六甲胎神（《天干六甲胎神歌》甲己之日占在门，乙庚碓磨休移动。丙辛厨灶莫相干，丁壬仓库忌修弄。戊癸房床若移整，犯之孕妇堕孩童。）
 *
 * @author 6tail
 */
class FetusHeavenStem: LoopTyme {

    constructor(name: String): super(NAMES, name)

    constructor(index: Int): super(NAMES, index)

    override fun next(n: Int): FetusHeavenStem {
        return FetusHeavenStem(nextIndex(n))
    }

    companion object {
        val NAMES: Array<String> = arrayOf("门", "碓磨", "厨灶", "仓库", "房床")

        @JvmStatic
        fun fromIndex(index: Int): FetusHeavenStem {
            return FetusHeavenStem(index)
        }

        @JvmStatic
        fun fromName(name: String): FetusHeavenStem {
            return FetusHeavenStem(name)
        }
    }
}

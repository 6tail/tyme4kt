package com.tyme.culture.pengzu

import com.tyme.LoopTyme

/**
 * 天干彭祖百忌
 *
 * @author 6tail
 */
class PengZuHeavenStem: LoopTyme {
    constructor(name: String): super(NAMES, name)

    constructor(index: Int): super(NAMES, index)

    override fun next(n: Int): PengZuHeavenStem {
        return PengZuHeavenStem(nextIndex(n))
    }

    companion object {
        val NAMES: Array<String> = arrayOf("甲不开仓财物耗散", "乙不栽植千株不长", "丙不修灶必见灾殃", "丁不剃头头必生疮", "戊不受田田主不祥", "己不破券二比并亡", "庚不经络织机虚张", "辛不合酱主人不尝", "壬不泱水更难提防", "癸不词讼理弱敌强")

        @JvmStatic
        fun fromIndex(index: Int): PengZuHeavenStem {
            return PengZuHeavenStem(index)
        }

        @JvmStatic
        fun fromName(name: String): PengZuHeavenStem {
            return PengZuHeavenStem(name)
        }
    }
}

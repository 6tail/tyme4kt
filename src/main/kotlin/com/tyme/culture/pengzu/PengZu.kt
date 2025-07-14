package com.tyme.culture.pengzu

import com.tyme.AbstractCulture
import com.tyme.sixtycycle.SixtyCycle

/**
 * 彭祖百忌
 *
 * @author 6tail
 */
class PengZu(sixtyCycle: SixtyCycle) : AbstractCulture() {

    /** 天干彭祖百忌 */
    private var pengZuHeavenStem: PengZuHeavenStem = PengZuHeavenStem(sixtyCycle.getHeavenStem().getIndex())

    /** 地支彭祖百忌 */
    private var pengZuEarthBranch: PengZuEarthBranch = PengZuEarthBranch(sixtyCycle.getEarthBranch().getIndex())

    fun getPengZuHeavenStem(): PengZuHeavenStem {
        return pengZuHeavenStem
    }

    fun getPengZuEarthBranch(): PengZuEarthBranch {
        return pengZuEarthBranch
    }

    override fun getName(): String {
        return "$pengZuHeavenStem $pengZuEarthBranch"
    }

    companion object {
        @JvmStatic
        fun fromSixtyCycle(sixtyCycle: SixtyCycle): PengZu {
            return PengZu(sixtyCycle)
        }
    }
}

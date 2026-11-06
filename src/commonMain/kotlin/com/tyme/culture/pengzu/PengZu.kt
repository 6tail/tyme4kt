package com.tyme.culture.pengzu

import com.tyme.AbstractCulture
import com.tyme.sixtycycle.SixtyCycle
import kotlin.jvm.JvmStatic

/**
 * 彭祖百忌
 *
 * @author 6tail
 */
class PengZu(sixtyCycle: SixtyCycle): AbstractCulture() {

    /**
     * 天干彭祖百忌
     */
    val pengZuHeavenStem: PengZuHeavenStem = PengZuHeavenStem(sixtyCycle.getHeavenStem().index)

    /**
     * 地支彭祖百忌
     */
    val pengZuEarthBranch: PengZuEarthBranch = PengZuEarthBranch(sixtyCycle.getEarthBranch().index)

    override fun getName(): String {
        return "$pengZuHeavenStem $pengZuEarthBranch"
    }

    companion object {
        /**
         * 从干支初始化
         *
         * @param sixtyCycle 干支
         * @return 彭祖百忌
         */
        @JvmStatic
        fun fromSixtyCycle(sixtyCycle: SixtyCycle): PengZu {
            return PengZu(sixtyCycle)
        }
    }
}

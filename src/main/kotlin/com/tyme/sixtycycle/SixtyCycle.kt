package com.tyme.sixtycycle

import com.tyme.LoopTyme
import com.tyme.culture.Sound
import com.tyme.culture.Ten
import com.tyme.culture.pengzu.PengZu

/**
 * 六十甲子(六十干支周)
 *
 * @author 6tail
 */
class SixtyCycle: LoopTyme {

    constructor(index: Int): super(NAMES, index)

    constructor(name: String): super(NAMES, name)

    /**
     * 天干
     *
     * @return 天干
     */
    fun getHeavenStem(): HeavenStem {
        return HeavenStem(getIndex() % HeavenStem.NAMES.size)
    }

    /**
     * 地支
     *
     * @return 地支
     */
    fun getEarthBranch(): EarthBranch {
        return EarthBranch(getIndex() % EarthBranch.NAMES.size)
    }

    /**
     * 纳音
     *
     * @return 纳音
     */
    fun getSound(): Sound {
        return Sound(getIndex() / 2)
    }

    /**
     * 彭祖百忌
     *
     * @return 彭祖百忌
     */
    fun getPengZu(): PengZu {
        return PengZu(this)
    }

    /**
     * 旬
     *
     * @return 旬
     */
    fun getTen(): Ten {
        return Ten((getHeavenStem().getIndex() - getEarthBranch().getIndex()) / 2)
    }

    /**
     * 旬空(空亡)，因地支比天干多2个，旬空则为每一轮干支一一配对后多出来的2个地支
     *
     * @return 旬空(空亡)
     */
    fun getExtraEarthBranches(): Array<EarthBranch> {
        val eb = EarthBranch(10 + getEarthBranch().getIndex() - getHeavenStem().getIndex())
        return arrayOf(eb, eb.next(1))
    }

    override fun next(n: Int): SixtyCycle {
        return SixtyCycle(nextIndex(n))
    }

    companion object {
        val NAMES: Array<String> = arrayOf("甲子", "乙丑", "丙寅", "丁卯", "戊辰", "己巳", "庚午", "辛未", "壬申", "癸酉", "甲戌", "乙亥", "丙子", "丁丑", "戊寅", "己卯", "庚辰", "辛巳", "壬午", "癸未", "甲申", "乙酉", "丙戌", "丁亥", "戊子", "己丑", "庚寅", "辛卯", "壬辰", "癸巳", "甲午", "乙未", "丙申", "丁酉", "戊戌", "己亥", "庚子", "辛丑", "壬寅", "癸卯", "甲辰", "乙巳", "丙午", "丁未", "戊申", "己酉", "庚戌", "辛亥", "壬子", "癸丑", "甲寅", "乙卯", "丙辰", "丁巳", "戊午", "己未", "庚申", "辛酉", "壬戌", "癸亥")

        @JvmStatic
        fun fromIndex(index: Int): SixtyCycle {
            return SixtyCycle(index)
        }

        @JvmStatic
        fun fromName(name: String): SixtyCycle {
            return SixtyCycle(name)
        }
    }
}

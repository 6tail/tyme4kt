package com.tyme.sixtycycle

import com.tyme.LoopTyme
import com.tyme.culture.Direction
import com.tyme.culture.Element
import com.tyme.culture.Terrain
import com.tyme.culture.pengzu.PengZuHeavenStem
import com.tyme.culture.star.ten.TenStar
import com.tyme.enums.YinYang
import kotlin.jvm.JvmStatic

/**
 * 天干（天元）
 *
 * @author 6tail
 */
class HeavenStem: LoopTyme {
    constructor(index: Int) : super(NAMES, index)

    constructor(name: String) : super(NAMES, name)

    override fun next(n: Int): HeavenStem {
        return HeavenStem(nextIndex(n))
    }

    /**
     * 五行
     *
     * @return 五行
     */
    fun getElement(): Element {
        return Element(getIndex() / 2)
    }

    /**
     * 阴阳
     *
     * @return 阴阳
     */
    fun getYinYang(): YinYang {
        return if (getIndex() % 2 == 0) YinYang.YANG else YinYang.YIN
    }

    /**
     * 十神（生我者，正印偏印。我生者，伤官食神。克我者，正官七杀。我克者，正财偏财。同我者，劫财比肩。）
     *
     * @param target 天干
     * @return 十神
     */
    fun getTenStar(target: HeavenStem): TenStar {
        val index: Int = getIndex()
        val targetIndex: Int = target.getIndex()
        var offset: Int = targetIndex - index
        if (index % 2 != 0 && targetIndex % 2 == 0) {
            offset += 2
        }
        return TenStar(offset)
    }

    /**
     * 方位
     *
     * @return 方位
     */
    fun getDirection(): Direction {
        return getElement().getDirection()
    }

    /**
     * 喜神方位（《喜神方位歌》甲己在艮乙庚乾，丙辛坤位喜神安。丁壬只在离宫坐，戊癸原在在巽间。）
     *
     * @return 方位
     */
    fun getJoyDirection(): Direction {
        return Direction(intArrayOf(7, 5, 1, 8, 3)[getIndex() % 5])
    }

    /**
     * 阳贵神方位（《阳贵神歌》甲戊坤艮位，乙己是坤坎，庚辛居离艮，丙丁兑与乾，震巽属何日，壬癸贵神安。）
     *
     * @return 方位
     */
    fun getYangDirection(): Direction {
        return Direction(intArrayOf(1, 1, 6, 5, 7, 0, 8, 7, 2, 3)[getIndex()])
    }

    /**
     * 阴贵神方位（《阴贵神歌》甲戊见牛羊，乙己鼠猴乡，丙丁猪鸡位，壬癸蛇兔藏，庚辛逢虎马，此是贵神方。）
     *
     * @return 方位
     */
    fun getYinDirection(): Direction {
        return Direction(intArrayOf(7, 0, 5, 6, 1, 1, 7, 8, 3, 2)[getIndex()])
    }

    /**
     * 财神方位（《财神方位歌》甲乙东北是财神，丙丁向在西南寻，戊己正北坐方位，庚辛正东去安身，壬癸原来正南坐，便是财神方位真。）
     *
     * @return 方位
     */
    fun getWealthDirection(): Direction {
        return Direction(intArrayOf(7, 1, 0, 2, 8)[getIndex() / 2])
    }

    /**
     * 福神方位（《福神方位歌》甲乙东南是福神，丙丁正东是堪宜，戊北己南庚辛坤，壬在乾方癸在西。）
     *
     * @return 方位
     */
    fun getMascotDirection(): Direction {
        return Direction(intArrayOf(3, 3, 2, 2, 0, 8, 1, 1, 5, 6)[getIndex()])
    }

    /**
     * 天干彭祖百忌
     *
     * @return 天干彭祖百忌
     */
    fun getPengZuHeavenStem(): PengZuHeavenStem {
        return PengZuHeavenStem(getIndex())
    }

    /**
     * 地势(长生十二神)
     *
     * @param earthBranch 地支
     * @return 地势(长生十二神)
     */
    fun getTerrain(earthBranch: EarthBranch): Terrain {
        val earthBranchIndex: Int = earthBranch.getIndex()
        return Terrain(intArrayOf(1, 6, 10, 9, 10, 9, 7, 0, 4, 3)[getIndex()] + (if (YinYang.YANG == getYinYang()) earthBranchIndex else -earthBranchIndex))
    }

    /**
     * 五合（甲己合，乙庚合，丙辛合，丁壬合，戊癸合）
     *
     * @return 天干
     */
    fun getCombine(): HeavenStem {
        return next(5)
    }

    /**
     * 合化（甲己合化土，乙庚合化金，丙辛合化水，丁壬合化木，戊癸合化火）
     *
     * @param target 天干
     * @return 五行，如果无法合化，返回null
     */
    fun combine(target: HeavenStem?): Element? {
        return if (getCombine() == target) Element(getIndex() + 2) else null
    }

    companion object {
        val NAMES: Array<String> = arrayOf("甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸")

        @JvmStatic
        fun fromIndex(index: Int): HeavenStem {
            return HeavenStem(index)
        }

        @JvmStatic
        fun fromName(name: String): HeavenStem {
            return HeavenStem(name)
        }
    }
}

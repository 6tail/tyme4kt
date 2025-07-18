package com.tyme.culture.phenology

import com.tyme.LoopTyme
import com.tyme.jd.JulianDay
import com.tyme.util.ShouXingUtil
import kotlin.math.PI


/**
 * 候
 *
 * @author 6tail
 */
class Phenology: LoopTyme {
    /**
     * 年
     */
    private var year: Int

    /**
     * 从名称初始化
     *
     * @param year 年
     * @param name 名称
     * @return 候
     */
    constructor(year: Int, name: String): super(NAMES, name){
        this.year = year
    }

    /**
     * 从索引初始化
     *
     * @param year  年
     * @param index 索引
     * @return 候
     */
    constructor(year: Int, index: Int): super(NAMES, index){
        val size = getSize()
        this.year = (year * size + index) / size
    }

    fun getYear(): Int {
        return year
    }

    /**
     * 三候
     *
     * @return 三候
     */
    fun getThreePhenology(): ThreePhenology {
        return ThreePhenology(getIndex() % 3)
    }

    override fun next(n: Int): Phenology {
        val size = getSize()
        val i = getIndex() + n
        return Phenology((year * size + i) / size, indexOf(i))
    }

    /**
     * 儒略日
     *
     * @return 儒略日
     */
    fun getJulianDay(): JulianDay {
        val t = ShouXingUtil.saLonT((year - 2000 + (getIndex() - 18) * 5.0 / 360 + 1) * 2 * PI)
        return JulianDay(t * 36525 + JulianDay.J2000 + 8.0 / 24 - ShouXingUtil.dtT(t * 36525))
    }

    companion object {
        val NAMES: Array<String> = arrayOf("蚯蚓结", "麋角解", "水泉动", "雁北乡", "鹊始巢", "雉始雊", "鸡始乳", "征鸟厉疾", "水泽腹坚", "东风解冻", "蛰虫始振", "鱼陟负冰", "獭祭鱼", "候雁北", "草木萌动", "桃始华", "仓庚鸣", "鹰化为鸠", "玄鸟至", "雷乃发声", "始电", "桐始华", "田鼠化为鴽", "虹始见", "萍始生", "鸣鸠拂其羽", "戴胜降于桑", "蝼蝈鸣", "蚯蚓出", "王瓜生", "苦菜秀", "靡草死", "麦秋至", "螳螂生", "鵙始鸣", "反舌无声", "鹿角解", "蜩始鸣", "半夏生", "温风至", "蟋蟀居壁", "鹰始挚", "腐草为萤", "土润溽暑", "大雨行时", "凉风至", "白露降", "寒蝉鸣", "鹰乃祭鸟", "天地始肃", "禾乃登", "鸿雁来", "玄鸟归", "群鸟养羞", "雷始收声", "蛰虫坯户", "水始涸", "鸿雁来宾", "雀入大水为蛤", "菊有黄花", "豺乃祭兽", "草木黄落", "蛰虫咸俯", "水始冰", "地始冻", "雉入大水为蜃", "虹藏不见", "天气上升地气下降", "闭塞而成冬", "鹖鴠不鸣", "虎始交", "荔挺出")

        @JvmStatic
        fun fromIndex(year: Int, index: Int): Phenology {
            return Phenology(year, index)
        }

        @JvmStatic
        fun fromName(year: Int, name: String): Phenology {
            return Phenology(year, name)
        }
    }
}

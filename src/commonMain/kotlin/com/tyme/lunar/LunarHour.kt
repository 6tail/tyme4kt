package com.tyme.lunar

import com.tyme.culture.Taboo
import com.tyme.culture.ren.MinorRen
import com.tyme.culture.star.nine.NineStar
import com.tyme.culture.star.twelve.TwelveStar
import com.tyme.eightchar.EightChar
import com.tyme.eightchar.provider.EightCharProvider
import com.tyme.eightchar.provider.impl.DefaultEightCharProvider
import com.tyme.sixtycycle.EarthBranch
import com.tyme.sixtycycle.HeavenStem
import com.tyme.sixtycycle.SixtyCycle
import com.tyme.sixtycycle.SixtyCycleHour
import com.tyme.solar.SolarDay
import com.tyme.solar.SolarTerm
import com.tyme.solar.SolarTime
import com.tyme.unit.SecondUnit
import kotlin.jvm.JvmStatic

/**
 * 农历时辰
 *
 * @author 6tail
 */
class LunarHour(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int): SecondUnit(year, month, day, hour, minute, second) {

    init {
        validate(year, month, day, hour, minute, second)
    }

    /**
     * 农历日
     */
    val lunarDay: LunarDay
        get() = LunarDay(year, month, day)

    override fun getName(): String {
        return "${EarthBranch(indexInDay).getName()}时"
    }

    override fun toString(): String {
        return "${lunarDay}${getSixtyCycle().getName()}时"
    }

    /**
     * 位于当天的索引
     */
    val indexInDay: Int
        get() = (hour + 1) / 2

    override fun next(n: Int): LunarHour {
        if (n == 0) {
            return LunarHour(year, month, day, hour, minute, second)
        }
        val h: Int = hour + n * 2
        val d: LunarDay = lunarDay.next(h.floorDiv(24))
        return LunarHour(d.year, d.month, d.day, indexOf(h, 24), minute, second)
    }

    /**
     * 是否在指定农历时辰之前
     *
     * @param target 农历时辰
     * @return true/false
     */
    fun isBefore(target: LunarHour): Boolean {
        return getCompareIndex() < target.getCompareIndex()
    }

    /**
     * 是否在指定农历时辰之后
     *
     * @param target 农历时辰
     * @return true/false
     */
    fun isAfter(target: LunarHour): Boolean {
        return getCompareIndex() > target.getCompareIndex()
    }

    /**
     * 干支
     *
     * @return 干支
     */
    fun getSixtyCycle(): SixtyCycle {
        var e: Int = indexInDay
        var h: HeavenStem = lunarDay.getSixtyCycle().getHeavenStem()
        if (hour >= 23) {
            h = h.next(1)
            e = 0
        }
        return SixtyCycle(h.index * 12 + e)
    }

    /**
     * 黄道黑道十二神
     *
     * @return 黄道黑道十二神
     */
    fun getTwelveStar(): TwelveStar {
        return TwelveStar(getSixtyCycle().getEarthBranch().index + (8 - getSixtyCycleHour().day.getEarthBranch().index % 6) * 2)
    }

    /**
     * 九星（时家紫白星歌诀：三元时白最为佳，冬至阳生顺莫差，孟日七宫仲一白，季日四绿发萌芽，每把时辰起甲子，本时星耀照光华，时星移入中宫去，顺飞八方逐细查。夏至阴生逆回首，孟归三碧季加六，仲在九宫时起甲，依然掌中逆轮跨。）
     *
     * @return 九星
     */
    fun getNineStar(): NineStar {
        val solar: SolarDay = lunarDay.getSolarDay()
        val dongZhi = SolarTerm(solar.year, 0)
        val earthBranchIndex: Int = indexInDay % 12
        var index: Int = 8 - 3 * (lunarDay.getSixtyCycle().getEarthBranch().index % 3)
        if (!solar.isBefore(dongZhi.getJulianDay().getSolarDay()) && solar.isBefore(dongZhi.next(12).getJulianDay().getSolarDay())) {
            index = 8 + earthBranchIndex - index
        } else {
            index -= earthBranchIndex
        }
        return NineStar(index)
    }

    /**
     * 公历时刻
     *
     * @return 公历时刻
     */
    fun getSolarTime(): SolarTime {
        val d: SolarDay = lunarDay.getSolarDay()
        return SolarTime(d.year, d.month, d.day, hour, minute, second)
    }

    /**
     * 干支时辰
     *
     * @return 干支时辰
     */
    fun getSixtyCycleHour(): SixtyCycleHour {
        return getSolarTime().getSixtyCycleHour()
    }

    /**
     * 八字
     *
     * @return 八字
     */
    fun getEightChar(): EightChar {
        return provider.getEightChar(this)
    }

    /**
     * 宜
     *
     * @return 宜忌列表
     */
    fun getRecommends(): List<Taboo> {
        return Taboo.getHourRecommends(getSixtyCycleHour().day, getSixtyCycle())
    }

    /**
     * 忌
     *
     * @return 宜忌列表
     */
    fun getAvoids(): List<Taboo> {
        return Taboo.getHourAvoids(getSixtyCycleHour().day, getSixtyCycle())
    }

    /**
     * 小六壬
     *
     * @return 小六壬
     */
    fun getMinorRen(): MinorRen {
        return lunarDay.getMinorRen().next(indexInDay)
    }

    companion object {
        /**
         * 八字计算接口
         */
        var provider: EightCharProvider = DefaultEightCharProvider()

        @JvmStatic
        fun validate(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int) {
            validate(hour, minute, second)
            LunarDay.validate(year, month, day)
        }

        /**
         * 从农历年月日时分秒初始化
         *
         * @param year   农历年
         * @param month  农历月，闰月为负
         * @param day    农历日
         * @param hour   时
         * @param minute 分
         * @param second 秒
         */
        @JvmStatic
        fun fromYmdHms(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int): LunarHour {
            return LunarHour(year, month, day, hour, minute, second)
        }
    }
}

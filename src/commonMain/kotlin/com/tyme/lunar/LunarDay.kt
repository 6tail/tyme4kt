package com.tyme.lunar

import com.tyme.culture.*
import com.tyme.culture.fetus.FetusDay
import com.tyme.culture.ren.MinorRen
import com.tyme.culture.star.nine.NineStar
import com.tyme.culture.star.six.SixStar
import com.tyme.culture.star.twelve.TwelveStar
import com.tyme.culture.star.twentyeight.TwentyEightStar
import com.tyme.festival.LunarFestival
import com.tyme.sixtycycle.SixtyCycle
import com.tyme.sixtycycle.SixtyCycleDay
import com.tyme.sixtycycle.ThreePillars
import com.tyme.solar.SolarDay
import com.tyme.unit.AbstractDay
import kotlin.jvm.JvmStatic
import kotlin.math.abs

/**
 * 农历日
 *
 * @author 6tail
 */
class LunarDay(year: Int, month: Int, day: Int): AbstractDay(year, month, day) {

    init {
        validate(year, month, day)
    }

    /**
     * 农历月
     */
    val lunarMonth: LunarMonth
        get() = LunarMonth(year, month)

    override fun getName(): String {
        return NAMES[day - 1]
    }

    override fun toString(): String {
        return lunarMonth.toString() + getName()
    }

    override fun next(n: Int): LunarDay {
        return getSolarDay().next(n).getLunarDay()
    }

    /**
     * 是否在指定农历日之前
     *
     * @param target 农历日
     * @return true/false
     */
    fun isBefore(target: LunarDay): Boolean {
        return super.isBefore(target)
    }

    /**
     * 是否在指定农历日之后
     *
     * @param target 农历日
     * @return true/false
     */
    fun isAfter(target: LunarDay): Boolean {
        return super.isAfter(target)
    }

    override fun getWeek(): Week {
        return getSolarDay().getWeek()
    }

    /**
     * 干支
     *
     * @return 干支
     */
    fun getSixtyCycle(): SixtyCycle {
        return SixtyCycle(lunarMonth.getFirstJulianDay().next(day - 12).day.toInt())
    }

    /**
     * 建除十二值神
     *
     * @return 建除十二值神
     * @see SixtyCycleDay.getDuty
     */
    fun getDuty(): Duty {
        return getSixtyCycleDay().getDuty()
    }

    /**
     * 黄道黑道十二神
     *
     * @return 黄道黑道十二神
     * @see SixtyCycleDay.getTwelveStar
     */
    fun getTwelveStar(): TwelveStar {
        return getSixtyCycleDay().getTwelveStar()
    }

    /**
     * 九星（在冬至前后找到最近的甲子日为一白，往后二黑依次顺推；在夏至前后找到最近的甲子日为九紫，往后八白依次逆推。）
     *
     * @return 九星
     */
    fun getNineStar(): NineStar {
        return getSolarDay().getNineStar()
    }

    /**
     * 太岁方位
     *
     * @return 方位
     */
    fun getJupiterDirection(): Direction {
        return Direction.dayJupiter(LunarYear(year).getJupiterDirection(), getSixtyCycle())
    }

    /**
     * 逐日胎神
     *
     * @return 逐日胎神
     */
    fun getFetusDay(): FetusDay {
        return FetusDay.fromLunarDay(this)
    }

    /**
     * 月相第几天
     *
     * @return 月相第几天
     */
    fun getPhaseDay(): PhaseDay {
        return getSolarDay().getPhaseDay()
    }

    /**
     * 月相
     *
     * @return 月相
     */
    fun getPhase(): Phase {
        return getPhaseDay().getPhase()
    }

    /**
     * 六曜
     *
     * @return 六曜
     */
    fun getSixStar(): SixStar {
        return SixStar((abs(month) + day - 2) % 6)
    }

    /**
     * 公历日
     *
     * @return 公历日
     */
    fun getSolarDay(): SolarDay {
        return lunarMonth.getFirstJulianDay().next(day - 1).getSolarDay()
    }

    /**
     * 干支日
     *
     * @return 干支日
     */
    fun getSixtyCycleDay(): SixtyCycleDay {
        return getSolarDay().getSixtyCycleDay()
    }

    /**
     * 二十八宿
     *
     * @return 二十八宿
     */
    fun getTwentyEightStar(): TwentyEightStar {
        return TwentyEightStar.fromDay(getSixtyCycle(), getWeek())
    }

    /**
     * 农历传统节日，如果当天不是农历传统节日，返回null
     *
     * @return 农历传统节日
     */
    fun getFestival(): LunarFestival? {
        return LunarFestival.fromYmd(year, month, day)
    }

    /**
     * 当天的农历时辰列表
     *
     * @return 农历时辰列表
     */
    fun getHours(): List<LunarHour> {
        return List(13) { i ->
            LunarHour(year, month, day, if (0 == i) 0 else i * 2 - 1, 0, 0)
        }
    }

    /**
     * 神煞列表(吉神宜趋，凶神宜忌)
     *
     * @return 神煞列表
     * @see SixtyCycleDay.getGods
     */
    fun getGods(): List<God> {
        return getSixtyCycleDay().getGods()
    }

    /**
     * 宜
     *
     * @return 宜忌列表
     * @see SixtyCycleDay.getRecommends
     */
    fun getRecommends(): List<Taboo> {
        return getSixtyCycleDay().getRecommends()
    }

    /**
     * 忌
     *
     * @return 宜忌列表
     * @see SixtyCycleDay.getAvoids
     */
    fun getAvoids(): List<Taboo> {
        return getSixtyCycleDay().getAvoids()
    }

    /**
     * 小六壬
     *
     * @return 小六壬
     */
    fun getMinorRen(): MinorRen {
        return lunarMonth.getMinorRen().next(day - 1)
    }

    /**
     * 三柱
     *
     * @return 三柱
     */
    fun getThreePillars(): ThreePillars {
        return getSixtyCycleDay().getThreePillars()
    }

    companion object {
        val NAMES: Array<String> = arrayOf("初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九", "初十", "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十", "廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七", "廿八", "廿九", "三十")

        @JvmStatic
        fun validate(year: Int, month: Int, day: Int) {
            if (day < 1) {
                throw IllegalArgumentException("illegal lunar day: $day")
            }
            val m = LunarMonth(year, month)
            if (day > m.dayCount) {
                throw IllegalArgumentException("illegal day $day in $m")
            }
        }

        /**
         * 从农历年月日初始化
         *
         * @param year  农历年
         * @param month 农历月，闰月为负
         * @param day   农历日
         */
        @JvmStatic
        fun fromYmd(year: Int, month: Int, day: Int): LunarDay {
            return LunarDay(year, month, day)
        }
    }
}

package com.tyme.lunar

import com.tyme.AbstractTyme
import com.tyme.culture.*
import com.tyme.culture.fetus.FetusDay
import com.tyme.culture.ren.MinorRen
import com.tyme.culture.star.nine.NineStar
import com.tyme.culture.star.six.SixStar
import com.tyme.culture.star.twelve.TwelveStar
import com.tyme.culture.star.twentyeight.TwentyEightStar
import com.tyme.festival.LunarFestival
import com.tyme.sixtycycle.*
import com.tyme.solar.SolarDay
import com.tyme.solar.SolarTerm
import kotlin.jvm.JvmStatic
import kotlin.math.abs

/**
 * 农历日
 *
 * @author 6tail
 */
class LunarDay(
    /** 农历年 */
    year: Int,
    /** 农历月，闰月为负 */
    month: Int,
    /** 农历日 */
    private var day: Int
) : AbstractTyme() {
    /** 农历月 */
    private var month: LunarMonth
    /** 公历日（第一次使用时才会初始化） */
    private var solarDay: SolarDay? = null
    /** 干支日（第一次使用时才会初始化） */
    private var sixtyCycleDay: SixtyCycleDay? = null

    init {
        val m: LunarMonth = LunarMonth.fromYm(year, month)
        require(day in 1 ..m.getDayCount()) { "illegal day $day in $m" }
        this.month = m
    }

    /**
     * 农历月
     *
     * @return 农历月
     */
    fun getLunarMonth(): LunarMonth{
        return month
    }

    /**
     * 年
     *
     * @return 年
     */
    fun getYear(): Int {
        return month.getYear()
    }

    /**
     * 月
     *
     * @return 月
     */
    fun getMonth(): Int {
        return month.getMonthWithLeap()
    }

    /**
     * 日
     *
     * @return 日
     */
    fun getDay(): Int{
        return day
    }

    override fun getName(): String {
        return NAMES[day - 1]
    }

    override fun toString(): String {
        return month.toString() + getName()
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
        val aYear: Int = getYear()
        val bYear: Int = target.getYear()
        if (aYear != bYear) {
            return aYear < bYear
        }
        val aMonth: Int = getMonth()
        val bMonth: Int = target.getMonth()
        if (aMonth != bMonth) {
            return abs(aMonth) < abs(bMonth)
        }
        return day < target.getDay()
    }

    /**
     * 是否在指定农历日之后
     *
     * @param target 农历日
     * @return true/false
     */
    fun isAfter(target: LunarDay): Boolean {
        val aYear: Int = getYear()
        val bYear: Int = target.getYear()
        if (aYear != bYear) {
            return aYear > bYear
        }
        val aMonth: Int = getMonth()
        val bMonth: Int = target.getMonth()
        if (aMonth != bMonth) {
            return abs(aMonth) >= abs(bMonth)
        }
        return day > target.getDay()
    }

    /**
     * 星期
     *
     * @return 星期
     */
    fun getWeek(): Week {
        return getSolarDay().getWeek()
    }

    /**
     * 干支
     *
     * @return 干支
     */
    fun getSixtyCycle(): SixtyCycle {
        val offset: Int = month.getFirstJulianDay().next(day - 12).getDay().toInt()
        return SixtyCycle(HeavenStem(offset).getName() + EarthBranch(offset).getName())
    }

    /**
     * 建除十二值神
     *
     * @return 建除十二值神
     * @see SixtyCycleDay
     */
    fun getDuty(): Duty {
        return getSixtyCycleDay().getDuty()
    }

    /**
     * 黄道黑道十二神
     *
     * @return 黄道黑道十二神
     * @see SixtyCycleDay
     */
    fun getTwelveStar(): TwelveStar {
        return getSixtyCycleDay().getTwelveStar()
    }

    /**
     * 九星
     *
     * @return 九星
     */
    fun getNineStar(): NineStar {
        val d: SolarDay = getSolarDay()
        val dongZhi = SolarTerm(d.getYear(), 0)
        val dongZhiSolar: SolarDay = dongZhi.getSolarDay()
        val xiaZhiSolar: SolarDay = dongZhi.next(12).getSolarDay()
        val dongZhiSolar2: SolarDay = dongZhi.next(24).getSolarDay()
        val dongZhiIndex: Int = dongZhiSolar.getLunarDay().getSixtyCycle().getIndex()
        val xiaZhiIndex: Int = xiaZhiSolar.getLunarDay().getSixtyCycle().getIndex()
        val dongZhiIndex2: Int = dongZhiSolar2.getLunarDay().getSixtyCycle().getIndex()
        val solarShunBai: SolarDay = dongZhiSolar.next(if (dongZhiIndex > 29) 60 - dongZhiIndex else -dongZhiIndex)
        val solarShunBai2: SolarDay = dongZhiSolar2.next(if (dongZhiIndex2 > 29) 60 - dongZhiIndex2 else -dongZhiIndex2)
        val solarNiZi: SolarDay = xiaZhiSolar.next(if (xiaZhiIndex > 29) 60 - xiaZhiIndex else -xiaZhiIndex)
        var offset = 0
        if (!d.isBefore(solarShunBai) && d.isBefore(solarNiZi)) {
            offset = d.subtract(solarShunBai)
        } else if (!d.isBefore(solarNiZi) && d.isBefore(solarShunBai2)) {
            offset = 8 - d.subtract(solarNiZi)
        } else if (!d.isBefore(solarShunBai2)) {
            offset = d.subtract(solarShunBai2)
        } else if (d.isBefore(solarShunBai)) {
            offset = 8 + solarShunBai.subtract(d)
        }
        return NineStar(offset)
    }

    /**
     * 太岁方位
     *
     * @return 方位
     */
    fun getJupiterDirection(): Direction {
        val index: Int = getSixtyCycle().getIndex()
        return if (index % 12 < 6) Element(index / 12).getDirection() else month.getLunarYear().getJupiterDirection()
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
        val today = getSolarDay()
        val m = month.next(1)
        var p = Phase.fromIndex(m.getYear(), m.getMonthWithLeap(), 0)
        var d = p.getSolarDay()
        while (d.isAfter(today)) {
            p = p.next(-1)
            d = p.getSolarDay()
        }
        return PhaseDay(p, today.subtract(d))
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
        return SixStar((month.getMonth() + day - 2) % 6)
    }

    /**
     * 公历日
     *
     * @return 公历日
     */
    fun getSolarDay(): SolarDay {
        if (solarDay == null) {
            solarDay = month.getFirstJulianDay().next(day - 1).getSolarDay()
        }
        return solarDay!!
    }

    /**
     * 干支日
     *
     * @return 干支日
     */
    fun getSixtyCycleDay(): SixtyCycleDay {
        if (sixtyCycleDay == null) {
            sixtyCycleDay = getSolarDay().getSixtyCycleDay()
        }
        return sixtyCycleDay!!
    }

    /**
     * 二十八宿
     *
     * @return 二十八宿
     */
    fun getTwentyEightStar(): TwentyEightStar {
        return TwentyEightStar(intArrayOf(10, 18, 26, 6, 14, 22, 2)[getSolarDay().getWeek().getIndex()]).next(-7 * getSixtyCycle().getEarthBranch().getIndex())
    }

    /**
     * 农历传统节日，如果当天不是农历传统节日，返回null
     *
     * @return 农历传统节日
     */
    fun getFestival(): LunarFestival? {
        return LunarFestival.fromYmd(getYear(), getMonth(), day)
    }

    /**
     * 当天的农历时辰列表
     *
     * @return 农历时辰列表
     */
    fun getHours(): List<LunarHour> {
        val l: MutableList<LunarHour> = ArrayList()
        val y: Int = getYear()
        val m: Int = getMonth()
        l.add(LunarHour(y, m, day, 0, 0, 0))
        var i = 0
        while (i < 24) {
            l.add(LunarHour(y, m, day, i + 1, 0, 0))
            i += 2
        }
        return l
    }

    /**
     * 神煞列表(吉神宜趋，凶神宜忌)
     *
     * @return 神煞列表
     */
    fun getGods(): List<God> {
        return getSixtyCycleDay().getGods()
    }

    /**
     * 宜
     *
     * @return 宜忌列表
     */
    fun getRecommends(): List<Taboo> {
        return getSixtyCycleDay().getRecommends()
    }

    /**
     * 忌
     *
     * @return 宜忌列表
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
        return getLunarMonth().getMinorRen().next(day - 1)
    }

    /**
     * 三柱
     *
     * @return 三柱
     */
    fun getThreePillars(): ThreePillars {
        return getSixtyCycleDay().getThreePillars()
    }

    override fun equals(other: Any?): Boolean {
        return other is LunarDay && toString() == other.toString()
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    companion object {
        val NAMES: Array<String> = arrayOf("初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九", "初十", "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十", "廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七", "廿八", "廿九", "三十")

        @JvmStatic
        fun fromYmd(year: Int, month: Int, day: Int): LunarDay {
            return LunarDay(year, month, day)
        }
    }
}

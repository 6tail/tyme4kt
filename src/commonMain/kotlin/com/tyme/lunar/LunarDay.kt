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
import com.tyme.solar.SolarTerm
import com.tyme.unit.DayUnit
import kotlin.jvm.JvmStatic
import kotlin.math.abs

/**
 * 农历日
 *
 * @author 6tail
 */
class LunarDay(
    year: Int,
    month: Int,
    day: Int
) : DayUnit(year, month, day) {

    init {
        validate(year, month, day)
    }

    /**
     * 农历月
     *
     * @return 农历月
     */
    fun getLunarMonth(): LunarMonth {
        return LunarMonth(year, month)
    }

    override fun getName(): String {
        return NAMES[day - 1]
    }

    override fun toString(): String {
        return getLunarMonth().toString() + getName()
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
        if (year != target.year) {
            return year < target.year
        }
        if (month != target.month) {
            val t: Int = abs(target.month)
            return month == t || abs(month) < t
        }
        return day < target.day
    }

    /**
     * 是否在指定农历日之后
     *
     * @param target 农历日
     * @return true/false
     */
    fun isAfter(target: LunarDay): Boolean {
        if (year != target.year) {
            return year > target.year
        }
        if (month != target.month) {
            val t: Int = abs(month)
            return t == target.month || t > abs(target.month)
        }
        return day > target.day
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
        return SixtyCycle(getLunarMonth().getFirstJulianDay().next(day - 12).getDay().toInt())
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
        val winterSolstice: SolarDay = SolarTerm.fromIndex(d.year, 0).getSolarDay()
        val summerSolstice: SolarDay = SolarTerm.fromIndex(d.year, 12).getSolarDay()
        val nextWinterSolstice: SolarDay = SolarTerm.fromIndex(d.year + 1, 0).getSolarDay()
        // 距冬至最近的甲子日
        val w: SolarDay = winterSolstice.next(winterSolstice.getLunarDay().getSixtyCycle().stepsCloseTo(0))
        // 距夏至最近的甲子日
        val s: SolarDay = summerSolstice.next(summerSolstice.getLunarDay().getSixtyCycle().stepsCloseTo(0))
        // 距下个冬至最近的甲子日
        val n: SolarDay = nextWinterSolstice.next(nextWinterSolstice.getLunarDay().getSixtyCycle().stepsCloseTo(0))
        // 43210012345678876543210012345
        //      w        s        n
        //     冬至     夏至      冬至
        if (d.isBefore(w)) {
            return NineStar(w.subtract(d) - 1)
        }
        if (d.isBefore(s)) {
            return NineStar(d.subtract(w))
        }
        return NineStar(if (d.isBefore(n)) n.subtract(d) - 1 else d.subtract(n))
    }

    /**
     * 太岁方位
     *
     * @return 方位
     */
    fun getJupiterDirection(): Direction {
        val index: Int = getSixtyCycle().getIndex()
        return if (index % 12 < 6) Element(index / 12).getDirection() else getLunarMonth().getLunarYear().getJupiterDirection()
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
        val today: SolarDay = getSolarDay()
        val m: LunarMonth = getLunarMonth().next(1)
        var p: Phase = Phase.fromIndex(m.year, m.getMonthWithLeap(), 0)
        var d: SolarDay = p.getSolarDay()
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
        return SixStar((abs(month) + day - 2) % 6)
    }

    /**
     * 公历日
     *
     * @return 公历日
     */
    fun getSolarDay(): SolarDay {
        return getLunarMonth().getFirstJulianDay().next(day - 1).getSolarDay()
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
        return TwentyEightStar(intArrayOf(10, 18, 26, 6, 14, 22, 2)[getSolarDay().getWeek().getIndex()]).next(-7 * getSixtyCycle().getEarthBranch().getIndex())
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
        val l: MutableList<LunarHour> = ArrayList()
        l.add(LunarHour(year, month, day, 0, 0, 0))
        var i = 0
        while (i < 24) {
            l.add(LunarHour(year, month, day, i + 1, 0, 0))
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
        val NAMES: Array<String> = arrayOf(
            "初一",
            "初二",
            "初三",
            "初四",
            "初五",
            "初六",
            "初七",
            "初八",
            "初九",
            "初十",
            "十一",
            "十二",
            "十三",
            "十四",
            "十五",
            "十六",
            "十七",
            "十八",
            "十九",
            "二十",
            "廿一",
            "廿二",
            "廿三",
            "廿四",
            "廿五",
            "廿六",
            "廿七",
            "廿八",
            "廿九",
            "三十"
        )

        @JvmStatic
        fun validate(year: Int, month: Int, day: Int) {
            if (day < 1) {
                throw IllegalArgumentException("illegal lunar day: $day")
            }
            val m = LunarMonth(year, month)
            if (day > m.getDayCount()) {
                throw IllegalArgumentException("illegal day $day in $m")
            }
        }

        @JvmStatic
        fun fromYmd(year: Int, month: Int, day: Int): LunarDay {
            return LunarDay(year, month, day)
        }
    }
}

package com.tyme.sixtycycle

import com.tyme.AbstractTyme
import com.tyme.culture.*
import com.tyme.culture.fetus.FetusDay
import com.tyme.culture.star.nine.NineStar
import com.tyme.culture.star.twelve.TwelveStar
import com.tyme.culture.star.twentyeight.TwentyEightStar
import com.tyme.solar.SolarDay
import com.tyme.solar.SolarTerm
import com.tyme.solar.SolarTime
import kotlin.jvm.JvmStatic

/**
 * 干支日（立春换年，节令换月）
 *
 * @author 6tail
 */
class SixtyCycleDay: AbstractTyme {
    /** 公历日 */
    private var solarDay: SolarDay
    /** 干支月 */
    private var month: SixtyCycleMonth
    /** 日柱 */
    private var day: SixtyCycle

    constructor(solarDay: SolarDay, month: SixtyCycleMonth, day: SixtyCycle): super() {
        this.solarDay = solarDay
        this.month = month
        this.day = day
    }

    /**
     * 初始化
     *
     * @param solarDay 公历日
     */
    constructor(solarDay: SolarDay) {
        val term = solarDay.getTerm()
        val index = term.getIndex()
        val offset = if (index < 3) (if (index == 0) -2 else -1) else ((index - 3) / 2)
        this.solarDay = solarDay
        month = SixtyCycleYear.fromYear(term.getYear()).getFirstMonth().next(offset)
        day = SixtyCycle.fromIndex(solarDay.subtract(SolarDay.fromYmd(2000, 1, 7)))
    }

    /**
     * 公历日
     *
     * @return 公历日
     */
    fun getSolarDay(): SolarDay {
        return solarDay
    }

    /**
     * 干支月
     *
     * @return 干支月
     */
    fun getSixtyCycleMonth(): SixtyCycleMonth {
        return month
    }

    /**
     * 年柱
     * 当天所属的干支年干支，以立春换年。
     * @returns 干支 SixtyCycle。
     */
    fun getYear(): SixtyCycle {
        return month.getYear()
    }

    /**
     * 月柱
     * 当天所属的干支月干支，以节令换月。
     * @returns 干支 SixtyCycle。
     */
    fun getMonth(): SixtyCycle {
        return month.getSixtyCycle()
    }

    /**
     * 干支
     *
     * @return 干支
     */
    fun getSixtyCycle(): SixtyCycle {
        return day
    }

    override fun getName(): String {
        return "${day.getName()}日"
    }

    override fun toString(): String {
        return month.toString() + getName()
    }

    /**
     * 建除十二值神
     *
     * @return 建除十二值神
     */
    fun getDuty(): Duty {
        return Duty(day.getEarthBranch().getIndex() - getMonth().getEarthBranch().getIndex())
    }

    /**
     * 黄道黑道十二神
     *
     * @return 黄道黑道十二神
     */
    fun getTwelveStar(): TwelveStar {
        return TwelveStar(day.getEarthBranch().getIndex() + (8 - getMonth().getEarthBranch().getIndex() % 6) * 2)
    }

    /**
     * 九星
     *
     * @return 九星
     */
    fun getNineStar(): NineStar {
        val winterSolstice: SolarDay = SolarTerm.fromIndex(solarDay.year, 0).getSolarDay()
        val summerSolstice: SolarDay = SolarTerm.fromIndex(solarDay.year, 12).getSolarDay()
        val nextWinterSolstice: SolarDay = SolarTerm.fromIndex(solarDay.year + 1, 0).getSolarDay()
        val w: SolarDay = winterSolstice.next(winterSolstice.getLunarDay().getSixtyCycle().stepsCloseTo(0))
        val s: SolarDay = summerSolstice.next(summerSolstice.getLunarDay().getSixtyCycle().stepsCloseTo(0))
        val n: SolarDay = nextWinterSolstice.next(nextWinterSolstice.getLunarDay().getSixtyCycle().stepsCloseTo(0))
        if (solarDay.isBefore(w)) {
            return NineStar(w.subtract(solarDay) - 1)
        }
        if (solarDay.isBefore(s)) {
            return NineStar(solarDay.subtract(w))
        }
        return NineStar(if (solarDay.isBefore(n)) n.subtract(solarDay) - 1 else solarDay.subtract(n))
    }

    /**
     * 太岁方位
     *
     * @return 方位
     */
    fun getJupiterDirection(): Direction {
        val index: Int = day.getIndex()
        return if(index % 12 < 6) Element(index / 12).getDirection() else month.getSixtyCycleYear().getJupiterDirection()
    }

    /**
     * 逐日胎神
     *
     * @return 逐日胎神
     */
    fun getFetusDay(): FetusDay {
        return FetusDay.fromSixtyCycleDay(this)
    }

    /**
     * 二十八宿
     *
     * @return 二十八宿
     */
    fun getTwentyEightStar(): TwentyEightStar {
        return TwentyEightStar(intArrayOf(10, 18, 26, 6, 14, 22, 2)[solarDay.getWeek().getIndex()]).next(-7 * day.getEarthBranch().getIndex())
    }

    /**
     * 神煞列表(吉神宜趋，凶神宜忌)
     *
     * @return 神煞列表
     */
    fun getGods(): List<God> {
        return God.getDayGods(getMonth(), day)
    }

    /**
     * 宜
     *
     * @return 宜忌列表
     */
    fun getRecommends(): List<Taboo> {
        return Taboo.getDayRecommends(getMonth(), day)
    }

    /**
     * 忌
     *
     * @return 宜忌列表
     */
    fun getAvoids(): List<Taboo> {
        return Taboo.getDayAvoids(getMonth(), day)
    }

    /**
     * 推移
     *
     * @param n 推移天数
     * @return 干支日
     */
    override fun next(n: Int): SixtyCycleDay {
        return SixtyCycleDay(solarDay.next(n))
    }

    /**
     * 干支时辰列表
     *
     * @return 干支时辰列表
     */
    fun getHours(): List<SixtyCycleHour> {
        val l: MutableList<SixtyCycleHour> = mutableListOf()
        val d: SolarDay = solarDay.next(-1)
        var h = SixtyCycleHour(SolarTime(d.year, d.month, d.day, 23, 0, 0))
        l.add(h)
        (0 until 11).forEach { _ ->
            h = h.next(7200)
            l.add(h)
        }
        return l
    }

    /**
     * 三柱
     *
     * @return 三柱
     */
    fun getThreePillars(): ThreePillars {
        return ThreePillars(getYear(), getMonth(), getSixtyCycle())
    }

    companion object {
        @JvmStatic
        fun fromSolarDay(solarDay: SolarDay): SixtyCycleDay {
            return SixtyCycleDay(solarDay)
        }
    }
}

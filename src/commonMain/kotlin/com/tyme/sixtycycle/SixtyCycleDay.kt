package com.tyme.sixtycycle

import com.tyme.AbstractTyme
import com.tyme.culture.Direction
import com.tyme.culture.Duty
import com.tyme.culture.God
import com.tyme.culture.Taboo
import com.tyme.culture.fetus.FetusDay
import com.tyme.culture.star.nine.NineStar
import com.tyme.culture.star.twelve.TwelveStar
import com.tyme.culture.star.twentyeight.TwentyEightStar
import com.tyme.solar.SolarDay
import com.tyme.solar.SolarTime
import kotlin.jvm.JvmStatic

/**
 * 干支日（立春换年，节令换月）
 *
 * @author 6tail
 */
class SixtyCycleDay: AbstractTyme {
    /**
     * 公历日
     */
    private val solarDay: SolarDay

    /**
     * 干支月
     */
    val sixtyCycleMonth: SixtyCycleMonth

    /**
     * 年柱(当天所属的干支年干支，以立春换年。)
     */
    val year: SixtyCycle
        get() = sixtyCycleMonth.year

    /**
     * 月柱(当天所属的干支月干支，以节令换月。)
     */
    val month: SixtyCycle
        get() = sixtyCycleMonth.getSixtyCycle()

    /**
     * 日柱
     */
    private var day: SixtyCycle

    constructor(solarDay: SolarDay, month: SixtyCycleMonth, day: SixtyCycle) {
        this.solarDay = solarDay
        sixtyCycleMonth = month
        this.day = day
    }

    /**
     * 初始化
     *
     * @param solarDay 公历日
     */
    constructor(solarDay: SolarDay) {
        val term = solarDay.getTerm()
        val index = term.index
        val offset = if (index < 3) (if (index == 0) -2 else -1) else ((index - 3) / 2)
        this.solarDay = solarDay
        sixtyCycleMonth = SixtyCycleYear(term.year).firstMonth.next(offset)
        day = SixtyCycle(solarDay.subtract(SolarDay(2000, 1, 7)))
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
        return sixtyCycleMonth.toString() + getName()
    }

    /**
     * 建除十二值神
     *
     * @return 建除十二值神
     */
    fun getDuty(): Duty {
        return Duty(day.getEarthBranch().index - month.getEarthBranch().index)
    }

    /**
     * 黄道黑道十二神
     *
     * @return 黄道黑道十二神
     */
    fun getTwelveStar(): TwelveStar {
        return TwelveStar.fromDay(month, day)
    }

    /**
     * 九星
     *
     * @return 九星
     */
    fun getNineStar(): NineStar {
        return solarDay.getNineStar()
    }

    /**
     * 太岁方位
     *
     * @return 方位
     */
    fun getJupiterDirection(): Direction {
        return Direction.dayJupiter(sixtyCycleMonth.sixtyCycleYear.getJupiterDirection(), day)
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
        return TwentyEightStar.fromDay(day, solarDay.getWeek())
    }

    /**
     * 神煞列表(吉神宜趋，凶神宜忌)
     *
     * @return 神煞列表
     */
    fun getGods(): List<God> {
        return God.getDayGods(month, day)
    }

    /**
     * 宜
     *
     * @return 宜忌列表
     */
    fun getRecommends(): List<Taboo> {
        return Taboo.getDayRecommends(month, day)
    }

    /**
     * 忌
     *
     * @return 宜忌列表
     */
    fun getAvoids(): List<Taboo> {
        return Taboo.getDayAvoids(month, day)
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
        val l: MutableList<SixtyCycleHour> = ArrayList()
        val d: SolarDay = solarDay.next(-1)
        var h = SixtyCycleHour(SolarTime(d.year, d.month, d.day, 23, 0, 0))
        l.add(h)
        for(i in 0 until 11) {
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
        return ThreePillars(year, month, getSixtyCycle())
    }

    companion object {
        @JvmStatic
        fun fromSolarDay(solarDay: SolarDay): SixtyCycleDay {
            return SixtyCycleDay(solarDay)
        }
    }
}

package com.tyme.sixtycycle

import com.tyme.AbstractTyme
import com.tyme.culture.*
import com.tyme.culture.fetus.FetusDay
import com.tyme.culture.star.nine.NineStar
import com.tyme.culture.star.twelve.TwelveStar
import com.tyme.culture.star.twentyeight.TwentyEightStar
import com.tyme.lunar.LunarDay
import com.tyme.lunar.LunarMonth
import com.tyme.lunar.LunarYear
import com.tyme.solar.SolarDay
import com.tyme.solar.SolarTerm
import com.tyme.solar.SolarTime
import kotlin.jvm.JvmStatic
import kotlin.math.floor

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
        val solarYear: Int = solarDay.getYear()
        val springSolarDay: SolarDay = SolarTerm(solarYear, 3).getSolarDay()
        val lunarDay: LunarDay = solarDay.getLunarDay()
        var lunarYear: LunarYear = lunarDay.getLunarMonth().getLunarYear()
        if (lunarYear.getYear() == solarYear) {
            if (solarDay.isBefore(springSolarDay)) {
                lunarYear = lunarYear.next(-1)
            }
        } else if (lunarYear.getYear() < solarYear) {
            if (!solarDay.isBefore(springSolarDay)) {
                lunarYear = lunarYear.next(1)
            }
        }
        val term: SolarTerm = solarDay.getTerm()
        var index: Int = term.getIndex() - 3
        if (index < 0 && term.getSolarDay().isAfter(springSolarDay)) {
            index += 24
        }
        this.solarDay = solarDay
        month = SixtyCycleMonth(
            SixtyCycleYear(lunarYear.getYear()),
            LunarMonth.fromYm(solarYear, 1).getSixtyCycle().next(floor(index * 0.5).toInt())
        )
        day = lunarDay.getSixtyCycle()
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
        val solar: SolarDay = getSolarDay()
        val dongZhi = SolarTerm(solar.getYear(), 0)
        val xiaZhi: SolarTerm = dongZhi.next(12)
        val dongZhi2: SolarTerm = dongZhi.next(24)
        val dongZhiSolar: SolarDay = dongZhi.getSolarDay()
        val xiaZhiSolar: SolarDay = xiaZhi.getSolarDay()
        val dongZhiSolar2: SolarDay = dongZhi2.getSolarDay()
        val dongZhiIndex: Int = dongZhiSolar.getLunarDay().getSixtyCycle().getIndex()
        val xiaZhiIndex: Int = xiaZhiSolar.getLunarDay().getSixtyCycle().getIndex()
        val dongZhiIndex2: Int = dongZhiSolar2.getLunarDay().getSixtyCycle().getIndex()
        val solarShunBai: SolarDay = dongZhiSolar.next(if(dongZhiIndex > 29) 60 - dongZhiIndex else -dongZhiIndex)
        val solarShunBai2: SolarDay = dongZhiSolar2.next(if(dongZhiIndex2 > 29) 60 - dongZhiIndex2 else -dongZhiIndex2)
        val solarNiZi: SolarDay = xiaZhiSolar.next(if(xiaZhiIndex > 29) 60 - xiaZhiIndex else -xiaZhiIndex)
        var offset = 0
        if (!solar.isBefore(solarShunBai) && solar.isBefore(solarNiZi)) {
            offset = solar.subtract(solarShunBai)
        } else if (!solar.isBefore(solarNiZi) && solar.isBefore(solarShunBai2)) {
            offset = 8 - solar.subtract(solarNiZi)
        } else if (!solar.isBefore(solarShunBai2)) {
            offset = solar.subtract(solarShunBai2)
        } else if (solar.isBefore(solarShunBai)) {
            offset = 8 + solarShunBai.subtract(solar)
        }
        return NineStar(offset)
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
        var h = SixtyCycleHour(SolarTime(d.getYear(), d.getMonth(), d.getDay(), 23, 0, 0))
        l.add(h)
        for (i in 0 until 11) {
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

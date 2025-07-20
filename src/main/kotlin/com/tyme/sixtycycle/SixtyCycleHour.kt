package com.tyme.sixtycycle

import com.tyme.AbstractTyme
import com.tyme.culture.Taboo
import com.tyme.culture.star.nine.NineStar
import com.tyme.culture.star.twelve.TwelveStar
import com.tyme.eightchar.EightChar
import com.tyme.lunar.LunarDay
import com.tyme.lunar.LunarHour
import com.tyme.lunar.LunarMonth
import com.tyme.lunar.LunarYear
import com.tyme.solar.SolarDay
import com.tyme.solar.SolarTerm
import com.tyme.solar.SolarTime
import kotlin.jvm.JvmStatic
import kotlin.math.floor

/**
 * 干支时辰（立春换年，节令换月，23点换日）
 *
 * @author 6tail
 */
class SixtyCycleHour(
    /** 公历时刻 */
    private var solarTime: SolarTime
) : AbstractTyme() {
    /** 干支日 */
    private var day: SixtyCycleDay
    /** 时柱 */
    private var hour: SixtyCycle

    init {
        val solarYear: Int = solarTime.getYear()
        val springSolarTime: SolarTime = SolarTerm(solarYear, 3).getJulianDay().getSolarTime()
        val lunarHour: LunarHour = solarTime.getLunarHour()
        val lunarDay: LunarDay = lunarHour.getLunarDay()
        var lunarYear: LunarYear = lunarDay.getLunarMonth().getLunarYear()
        if (lunarYear.getYear() == solarYear) {
            if (solarTime.isBefore(springSolarTime)) {
                lunarYear = lunarYear.next(-1)
            }
        } else if (lunarYear.getYear() < solarYear) {
            if (!solarTime.isBefore(springSolarTime)) {
                lunarYear = lunarYear.next(1)
            }
        }
        val term: SolarTerm = solarTime.getTerm()
        var index: Int = term.getIndex() - 3
        if (index < 0 && term.getJulianDay().getSolarTime().isAfter(SolarTerm(solarYear, 3).getJulianDay().getSolarTime())) {
            index += 24
        }
        val d: SixtyCycle = lunarDay.getSixtyCycle()
        this.day = SixtyCycleDay(
            solarTime.getSolarDay(),
            SixtyCycleMonth(
                SixtyCycleYear(lunarYear.getYear()),
                LunarMonth.fromYm(solarYear, 1).getSixtyCycle().next(floor(index / 2f).toInt())
            ),
            if(solarTime.getHour() < 23) d else d.next(1))
        this.hour = lunarHour.getSixtyCycle()
    }


    /**
     * 年柱
     * 当时所属的干支年干支，以立春具体时刻换年。
     * @returns 干支 SixtyCycle。
     */
    fun getYear(): SixtyCycle {
        return day.getYear()
    }

    /**
     * 月柱
     * 当时所属的农历月干支，以节令具体时刻换月。
     * @returns 干支 SixtyCycle。
     */
    fun getMonth(): SixtyCycle {
        return day.getMonth()
    }

    /**
     * 日柱
     * 注意：23:00开始为第二天日干支。
     * @returns 干支 SixtyCycle。
     */
    fun getDay(): SixtyCycle {
        return day.getSixtyCycle()
    }

    /**
     * 干支
     *
     * @return 干支
     */
    fun getSixtyCycle(): SixtyCycle {
        return hour
    }

    /**
     * 干支日
     *
     * @return 干支日
     */
    fun getSixtyCycleDay(): SixtyCycleDay {
        return day
    }

    /**
     * 公历时刻
     *
     * @return 公历时刻
     */
    fun getSolarTime(): SolarTime {
        return solarTime
    }

    override fun getName(): String {
        return "${hour.getName()}时"
    }

    override fun toString(): String {
        return "${day}${getName()}"
    }

    /**
     * 位于当天的索引
     *
     * @return 索引
     */
    fun getIndexInDay(): Int {
        val h: Int = solarTime.getHour()
        return if(h == 23) 0 else ((h + 1) / 2)
    }

    /**
     * 时九星
     *
     * @return 九星
     */
    fun getNineStar(): NineStar {
        val solar: SolarDay = solarTime.getSolarDay()
        val dongZhi = SolarTerm(solar.getYear(), 0)
        val xiaZhi: SolarTerm = dongZhi.next(12)
        val asc: Boolean = !solar.isBefore(dongZhi.getJulianDay().getSolarDay()) && solar.isBefore(xiaZhi.getJulianDay().getSolarDay())
        var start: Int = intArrayOf(8, 5, 2)[getDay().getEarthBranch().getIndex() % 3]
        if (asc) {
            start = 8 - start
        }
        val earthBranchIndex: Int = getIndexInDay() % 12
        return NineStar(start + (if(asc) earthBranchIndex else -earthBranchIndex))
    }

    /**
     * 黄道黑道十二神
     *
     * @return 黄道黑道十二神
     */
    fun getTwelveStar(): TwelveStar {
        return TwelveStar(hour.getEarthBranch().getIndex() + (8 - getDay().getEarthBranch().getIndex() % 6) * 2)
    }

    /**
     * 宜
     *
     * @return 宜忌列表
     */
    fun getRecommends(): List<Taboo> {
        return Taboo.getHourRecommends(getDay(), hour)
    }

    /**
     * 忌
     *
     * @return 宜忌列表
     */
    fun getAvoids(): List<Taboo> {
        return Taboo.getHourAvoids(getDay(), hour)
    }

    /**
     * 推移
     *
     * @param n 推移秒数
     * @return 干支时辰
     */
    override fun next(n: Int): SixtyCycleHour {
        return SixtyCycleHour(solarTime.next(n))
    }

    /**
     * 八字
     *
     * @return 八字
     */
    fun getEightChar(): EightChar {
        return EightChar(getYear(), getMonth(), getDay(), hour)
    }

    companion object {
        @JvmStatic
        fun fromSolarTime(solarTime: SolarTime): SixtyCycleHour {
            return SixtyCycleHour(solarTime)
        }
    }
}
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
import com.tyme.solar.SolarTerm
import com.tyme.solar.SolarTime
import kotlin.jvm.JvmStatic
import kotlin.math.floor

/**
 * 干支时辰（立春换年，节令换月，23点换日）
 *
 * @author 6tail
 */
class SixtyCycleHour(private var solarTime: SolarTime): AbstractTyme() {
    /**
     * 干支日
     */
    val sixtyCycleDay: SixtyCycleDay

    /**
     * 年柱(当时所属的干支年干支，以立春具体时刻换年。)
     */
    val year: SixtyCycle
        get() = sixtyCycleDay.year

    /**
     * 月柱(当时所属的农历月干支，以节令具体时刻换月。)
     */
    val month: SixtyCycle
        get() = sixtyCycleDay.month

    /**
     * 日柱(注意：23:00开始为第二天日干支。)
     */
    val day: SixtyCycle
        get() = sixtyCycleDay.getSixtyCycle()

    /**
     * 时柱
     */
    private var hour: SixtyCycle

    init {
        val solarYear: Int = solarTime.year
        val springSolarTime: SolarTime = SolarTerm(solarYear, 3).getJulianDay().getSolarTime()
        val lunarHour: LunarHour = solarTime.getLunarHour()
        val lunarDay: LunarDay = lunarHour.lunarDay
        var lunarYear: LunarYear = lunarDay.lunarMonth.lunarYear
        if (lunarYear.year == solarYear) {
            if (solarTime.isBefore(springSolarTime)) {
                lunarYear = lunarYear.next(-1)
            }
        } else if (lunarYear.year < solarYear) {
            if (!solarTime.isBefore(springSolarTime)) {
                lunarYear = lunarYear.next(1)
            }
        }
        val term: SolarTerm = solarTime.getTerm()
        var index: Int = term.index - 3
        if (index < 0 && term.getJulianDay().getSolarTime().isAfter(SolarTerm(solarYear, 3).getJulianDay().getSolarTime())) {
            index += 24
        }
        val d: SixtyCycle = lunarDay.getSixtyCycle()
        this.sixtyCycleDay = SixtyCycleDay(solarTime.solarDay, SixtyCycleMonth(SixtyCycleYear(lunarYear.year), LunarMonth(solarYear, 1).getSixtyCycle().next(floor(index * 0.5).toInt())), if(solarTime.hour < 23) d else d.next(1))
        this.hour = lunarHour.getSixtyCycle()
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
        return "$sixtyCycleDay${getName()}"
    }

    /**
     * 位于当天的索引
     */
    val indexInDay: Int
        get() {
            val h: Int = solarTime.hour
            return if(h == 23) 0 else ((h + 1) / 2)
        }

    /**
     * 时九星
     *
     * @return 九星
     */
    fun getNineStar(): NineStar {
        return NineStar.fromHour(solarTime.solarDay, day, indexInDay)
    }

    /**
     * 黄道黑道十二神
     *
     * @return 黄道黑道十二神
     */
    fun getTwelveStar(): TwelveStar {
        return TwelveStar(hour.getEarthBranch().index + (8 - day.getEarthBranch().index % 6) * 2)
    }

    /**
     * 宜
     *
     * @return 宜忌列表
     */
    fun getRecommends(): List<Taboo> {
        return Taboo.getHourRecommends(day, hour)
    }

    /**
     * 忌
     *
     * @return 宜忌列表
     */
    fun getAvoids(): List<Taboo> {
        return Taboo.getHourAvoids(day, hour)
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
        return EightChar(year, month, day, hour)
    }

    companion object {
        @JvmStatic
        fun fromSolarTime(solarTime: SolarTime): SixtyCycleHour {
            return SixtyCycleHour(solarTime)
        }
    }
}
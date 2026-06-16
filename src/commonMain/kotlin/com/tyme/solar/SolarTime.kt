package com.tyme.solar

import com.tyme.culture.Phase
import com.tyme.culture.phenology.Phenology
import com.tyme.jd.JulianDay
import com.tyme.lunar.LunarHour
import com.tyme.sixtycycle.SixtyCycleHour
import com.tyme.unit.SecondUnit
import com.tyme.util.pad2
import kotlin.jvm.JvmStatic


/**
 * 公历时刻
 *
 * @author 6tail
 */
class SolarTime(
    year: Int,
    month: Int,
    day: Int,
    hour: Int,
    minute: Int,
    second: Int
) : SecondUnit(year, month, day, hour, minute, second) {

    init {
        validate(year, month, day, hour, minute, second)
    }

    /**
     * 公历日
     *
     * @return 公历日
     */
    fun getSolarDay(): SolarDay{
        return SolarDay.fromYmd(year, month, day)
    }

    override fun getName(): String {
        return "${hour.pad2()}:${minute.pad2()}:${second.pad2()}"
    }

    override fun toString(): String {
        return "${getSolarDay()} ${getName()}"
    }

    /**
     * 推移
     *
     * @param n 推移秒数
     * @return 公历时刻
     */
    override fun next(n: Int): SolarTime {
        if (n == 0) {
            return SolarTime(year, month, day, hour, minute, second)
        }
        val t = getSecondsInDay() + n
        val s = indexOf(t, 86400)
        val d = getSolarDay().next(t.floorDiv(86400))
        return SolarTime(d.year, d.month, d.day, s / 3600, s % 3600 / 60, s % 60)
    }

    /**
     * 是否在指定公历时刻之前
     *
     * @param target 公历时刻
     * @return true/false
     */
    fun isBefore(target: SolarTime): Boolean {
        return getCompareIndex() < target.getCompareIndex()
    }

    /**
     * 是否在指定公历时刻之后
     *
     * @param target 公历时刻
     * @return true/false
     */
    fun isAfter(target: SolarTime): Boolean {
        return getCompareIndex() > target.getCompareIndex()
    }

    /**
     * 节气
     *
     * @return 节气
     */
    fun getTerm(): SolarTerm {
        var term: SolarTerm = getSolarDay().getTerm()
        if (isBefore(term.getJulianDay().getSolarTime())) {
            term = term.next(-1)
        }
        return term
    }

    /**
     * 候
     *
     * @return 候
     */
    fun getPhenology(): Phenology {
        var p: Phenology = getSolarDay().getPhenology()
        if (isBefore(p.getJulianDay().getSolarTime())) {
            p = p.next(-1)
        }
        return p
    }

    /**
     * 儒略日
     *
     * @return 儒略日
     */
    fun getJulianDay(): JulianDay {
        return JulianDay.fromYmdHms(year, month, day, hour, minute, second)
    }

    /**
     * 公历时刻相减，获得相差秒数
     *
     * @param target 公历时刻
     * @return 秒数
     */
    fun subtract(target: SolarTime): Int {
        val t = getSolarDay().subtract(target.getSolarDay()) * 86400L + getSecondsInDay() - target.getSecondsInDay()
        if (t < Int.MIN_VALUE || t > Int.MAX_VALUE) {
            throw ArithmeticException("seconds difference exceeds int range: $t")
        }
        return t.toInt()
    }

    /**
     * 农历时辰
     *
     * @return 农历时辰
     */
    fun getLunarHour(): LunarHour {
        val d = getSolarDay().getLunarDay()
        return LunarHour(d.year, d.month, d.day, hour, minute, second)
    }

    /**
     * 干支时辰
     *
     * @return 干支时辰
     */
    fun getSixtyCycleHour(): SixtyCycleHour {
        return SixtyCycleHour(this)
    }

    /**
     * 月相
     *
     * @return 月相
     */
    fun getPhase(): Phase {
        val month = getLunarHour().getLunarDay().getLunarMonth().next(1)
        var p = Phase.fromIndex(month.year, month.getMonthWithLeap(), 0)
        while (p.getSolarTime().isAfter(this)) {
            p = p.next(-1)
        }
        return p
    }

    override fun equals(other: Any?): Boolean {
        return other is SolarTime && toString() == other.toString()
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    companion object {
        @JvmStatic
        fun validate(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int) {
            validate(hour, minute, second)
            SolarDay.validate(year, month, day)
        }

        @JvmStatic
        fun fromYmdHms(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int): SolarTime {
            return SolarTime(year, month, day, hour, minute, second)
        }
    }
}

package com.tyme.solar

import com.tyme.AbstractTyme
import com.tyme.culture.phenology.Phenology
import com.tyme.jd.JulianDay
import com.tyme.lunar.LunarHour
import com.tyme.sixtycycle.SixtyCycleHour
import com.tyme.util.pad2
import kotlin.jvm.JvmStatic


/**
 * 公历时刻
 *
 * @author 6tail
 */
class SolarTime(
    /** 年 */
    year: Int,
    /** 月 */
    month: Int,
    /** 日 */
    day: Int,
    /** 时 */
    private var hour: Int,
    /** 分 */
    private var minute: Int,
    /** 秒 */
    private var second: Int
) : AbstractTyme() {

    /** 公历日 */
    private var day: SolarDay

    init {
        require(hour in 0 .. 23) { "illegal hour: $hour" }
        require(minute in 0 .. 59) { "illegal minute: $minute" }
        require(second in 0 .. 59) { "illegal second: $second" }
        this.day = SolarDay(year, month, day)
    }

    /**
     * 公历日
     *
     * @return 公历日
     */
    fun getSolarDay(): SolarDay{
        return day
    }

    /**
     * 年
     *
     * @return 年
     */
    fun getYear(): Int {
        return day.getYear()
    }

    /**
     * 月
     *
     * @return 月
     */
    fun getMonth(): Int {
        return day.getMonth()
    }

    /**
     * 日
     *
     * @return 日
     */
    fun getDay(): Int {
        return day.getDay()
    }

    /**
     * 时
     *
     * @return 时
     */
    fun getHour(): Int {
        return hour
    }

    /**
     * 分
     *
     * @return 分
     */
    fun getMinute(): Int {
        return minute
    }

    /**
     * 秒
     *
     * @return 秒
     */
    fun getSecond(): Int {
        return second
    }

    override fun getName(): String {
        return "${hour.pad2()}:${minute.pad2()}:${second.pad2()}"
    }

    override fun toString(): String {
        return "$day ${getName()}"
    }

    /**
     * 推移
     *
     * @param n 推移秒数
     * @return 公历时刻
     */
    override fun next(n: Int): SolarTime {
        if (n == 0) {
            return SolarTime(getYear(), getMonth(), getDay(), hour, minute, second)
        }
        var ts: Int = second + n
        var tm: Int = minute + ts / 60
        ts %= 60
        if (ts < 0) {
            ts += 60
            tm -= 1
        }
        var th = hour + tm / 60
        tm %= 60
        if (tm < 0) {
            tm += 60
            th -= 1
        }
        var td = th / 24
        th %= 24
        if (th < 0) {
            th += 24
            td -= 1
        }

        val d: SolarDay = day.next(td)
        return SolarTime(d.getYear(), d.getMonth(), d.getDay(), th, tm, ts)
    }

    /**
     * 是否在指定公历时刻之前
     *
     * @param target 公历时刻
     * @return true/false
     */
    fun isBefore(target: SolarTime): Boolean {
        if (day != target.getSolarDay()) {
            return day.isBefore(target.getSolarDay())
        }
        if (hour != target.getHour()) {
            return hour < target.getHour()
        }
        return if (minute != target.getMinute()) minute < target.getMinute() else second < target.getSecond()
    }

    /**
     * 是否在指定公历时刻之后
     *
     * @param target 公历时刻
     * @return true/false
     */
    fun isAfter(target: SolarTime): Boolean {
        if (day != target.getSolarDay()) {
            return day.isAfter(target.getSolarDay())
        }
        if (hour != target.getHour()) {
            return hour > target.getHour()
        }
        return if (minute != target.getMinute()) minute > target.getMinute() else second > target.getSecond()
    }

    /**
     * 节气
     *
     * @return 节气
     */
    fun getTerm(): SolarTerm {
        var term: SolarTerm = day.getTerm()
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
        var p: Phenology = day.getPhenology()
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
        return JulianDay.fromYmdHms(day.getYear(), day.getMonth(), day.getDay(), hour, minute, second)
    }

    /**
     * 公历时刻相减，获得相差秒数
     *
     * @param target 公历时刻
     * @return 秒数
     */
    fun subtract(target: SolarTime): Int {
        var days = day.subtract(target.getSolarDay())
        val cs: Int = hour * 3600 + minute * 60 + second
        val ts: Int = target.getHour() * 3600 + target.getMinute() * 60 + target.getSecond()
        var seconds: Int = cs - ts
        if (seconds < 0) {
            seconds += 86400
            days--
        }
        seconds += days * 86400
        return seconds
    }

    /**
     * 农历时辰
     *
     * @return 农历时辰
     */
    fun getLunarHour(): LunarHour {
        val d = day.getLunarDay()
        return LunarHour(d.getYear(), d.getMonth(), d.getDay(), hour, minute, second)
    }

    /**
     * 干支时辰
     *
     * @return 干支时辰
     */
    fun getSixtyCycleHour(): SixtyCycleHour {
        return SixtyCycleHour(this)
    }

    override fun equals(other: Any?): Boolean {
        return other is SolarTime && toString() == other.toString()
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    companion object {
        @JvmStatic
        fun fromYmdHms(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int): SolarTime {
            return SolarTime(year, month, day, hour, minute, second)
        }
    }
}

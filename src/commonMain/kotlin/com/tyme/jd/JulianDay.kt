package com.tyme.jd

import com.tyme.AbstractTyme
import com.tyme.culture.Week
import com.tyme.solar.SolarDay
import com.tyme.solar.SolarTime
import com.tyme.unit.SecondUnit
import kotlin.jvm.JvmStatic
import kotlin.math.round

/**
 * 儒略日
 *
 * @author 6tail
 */
class JulianDay(val day: Double): AbstractTyme() {

    override fun getName(): String {
        return day.toString()
    }

    override fun next(n: Int): JulianDay {
        return JulianDay(day + n)
    }

    /**
     * 公历日
     *
     * @return 公历日
     */
    fun getSolarDay(): SolarDay {
        val u: SecondUnit = getSecondUnit(false)
        return SolarDay(u.year, u.month, u.day)
    }

    /**
     * 公历时刻
     *
     * @return 公历时刻
     */
    fun getSolarTime(): SolarTime {
        val u = getSecondUnit(true)
        val y: Int = u.year
        val m: Int = u.month
        val d: Int = u.day
        val hour: Int = u.hour
        val minute: Int = u.minute
        val second: Int = u.second
        return if (second < 60) SolarTime(y, m, d, hour, minute, second)
        else SolarTime(y, m, d, hour, minute, second - 60).next(60)
    }

    private fun getSecondUnit(withTime: Boolean): SecondUnit {
        var d: Int = (day + 0.5).toInt()
        var f: Double = day + 0.5 - d

        if (d >= 2299161) {
            val c: Int = ((d - 1867216.25) / 36524.25).toInt()
            d += 1 + c - (c * 0.25).toInt()
        }
        d += 1524
        var y: Int = ((d - 122.1) / 365.25).toInt()
        d -= (365.25 * y).toInt()
        var m: Int = (d / 30.601).toInt()
        d -= (30.601 * m).toInt()
        if (m > 13) {
            m -= 12
        } else {
            y -= 1
        }
        m -= 1
        y -= 4715
        var hour: Int = 0
        var minute: Int = 0
        var second: Int = 0
        if (withTime) {
            f *= 24.0
            hour = f.toInt()
            f -= hour
            f *= 60.0
            minute = f.toInt()
            f -= minute
            f *= 60.0
            second = round(f).toInt()
        }
        return SecondUnit(y, m, d, hour, minute, second)
    }

    /**
     * 星期
     *
     * @return 星期
     */
    fun getWeek(): Week {
        return Week((day + 0.5).toInt() + 7000001)
    }

    /**
     * 儒略日相减
     *
     * @param target 儒略日
     * @return 差
     */
    fun subtract(target: JulianDay): Double {
        return day - target.day
    }

    companion object {
        /**
         * 2000年儒略日数(2000-1-1 12:00:00 UTC)
         */
        const val J2000: Double = 2451545.0

        @JvmStatic
        fun fromJulianDay(jd: Double): JulianDay {
            return JulianDay(jd)
        }

        @JvmStatic
        fun fromYmdHms(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int): JulianDay {
            var y: Int = year
            var m: Int = month
            val d = day + ((second / 60.0 + minute) / 60 + hour) / 24
            var n = 0
            val g: Boolean = y * 372 + m * 31 + d.toInt() >= 588829
            if (m <= 2) {
                m += 12
                y--
            }
            if (g) {
                n = (y * 0.01).toInt()
                n = 2 - n + (n  * 0.25).toInt()
            }
            return JulianDay((365.25 * (y + 4716)).toInt() + (30.6001 * (m + 1)).toInt() + d + n - 1524.5)
        }
    }
}

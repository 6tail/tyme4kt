package com.tyme.jd

import com.tyme.AbstractTyme
import com.tyme.culture.Week
import com.tyme.solar.SolarDay
import com.tyme.solar.SolarTime
import kotlin.math.round

/**
 * 儒略日
 *
 * @author 6tail
 */
class JulianDay(private var day: Double) : AbstractTyme() {
    /**
     * 儒略日
     *
     * @return 儒略日
     */
    fun getDay(): Double{
        return day
    }

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
    fun getSolarDay(): SolarDay{
        return getSolarTime().getSolarDay()
    }

    /**
     * 公历时刻
     *
     * @return 公历时刻
     */
    fun getSolarTime(): SolarTime {
        var d: Int = (day + 0.5).toInt()
        var f: Double = day + 0.5 - d

        if (d >= 2299161) {
            val c: Int = ((d - 1867216.25) / 36524.25).toInt()
            d += 1 + c - (c * 0.25).toInt()
        }
        d += 1524
        var year: Int = ((d - 122.1) / 365.25).toInt()
        d -= (365.25 * year).toInt()
        var month = (d / 30.601).toInt()
        d -= (30.601 * month).toInt()
        val day: Int = d
        if (month > 13) {
            month -= 12
        } else {
            year -= 1
        }
        month -= 1
        year -= 4715
        f *= 24.0
        val hour: Int = f.toInt()

        f -= hour
        f *= 60.0
        val minute: Int = f.toInt()

        f -= minute
        f *= 60.0
        val second: Int = round(f).toInt()
        return if (second < 60) SolarTime(year, month, day, hour, minute, second) else SolarTime(year, month, day, hour, minute, second - 60).next(60)
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
        return day - target.getDay()
    }

    companion object {
        /** 2000年儒略日数(2000-1-1 12:00:00 UTC) */
        const val J2000: Double = 2451545.0

        @JvmStatic
        fun fromYmdHms(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int): JulianDay {
            var y: Int = year
            var m: Int = month
            val d = day + ((second * 1.0 / 60 + minute) / 60 + hour) / 24
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

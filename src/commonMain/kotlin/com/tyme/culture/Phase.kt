package com.tyme.culture

import com.tyme.LoopTyme
import com.tyme.jd.JulianDay
import com.tyme.lunar.LunarDay
import com.tyme.lunar.LunarMonth
import com.tyme.solar.SolarDay
import com.tyme.solar.SolarTime
import com.tyme.util.ShouXingUtil
import com.tyme.util.ShouXingUtil.dtT
import com.tyme.util.ShouXingUtil.msaLonT
import kotlin.jvm.JvmStatic
import kotlin.math.floor

/**
 * 月相
 *
 * @author 6tail
 */
class Phase: LoopTyme {
    private var lunarYear: Int
    private var lunarMonth: Int

    constructor(lunarYear: Int, lunarMonth: Int, index: Int): super(NAMES, index) {
        val m = LunarMonth.fromYm(lunarYear, lunarMonth).next(index / getSize())
        this.lunarYear = m.getYear()
        this.lunarMonth = m.getMonthWithLeap()
    }

    constructor(lunarYear: Int, lunarMonth: Int, name: String): super(NAMES, name) {
        this.lunarYear = lunarYear
        this.lunarMonth = lunarMonth
    }

    override fun next(n: Int): Phase {
        val size = getSize()
        var i = getIndex() + n
        if (i < 0) {
            i -= size
        }
        i /= size
        var m = LunarMonth.fromYm(lunarYear, lunarMonth)
        if (i != 0) {
            m = m.next(i)
        }
        return fromIndex(m.getYear(), m.getMonthWithLeap(), nextIndex(n))
    }

    protected fun getStartSolarTime(): SolarTime {
        val n = floor((lunarYear - 2000) * 365.2422 / 29.53058886).toInt()
        var i = 0
        val d = LunarDay.fromYmd(lunarYear, lunarMonth, 1).getSolarDay()
        val jd = JulianDay.J2000 + ShouXingUtil.ONE_THIRD
        while (true) {
            val t = msaLonT((n + i) * ShouXingUtil.PI_2) * 36525
            if (!JulianDay.fromJulianDay(jd + t - dtT(t)).getSolarDay().isBefore(d)) {
                break
            }
            i++
        }
        val r = intArrayOf(0, 90, 180, 270)
        val t = msaLonT((n + i + r[getIndex() / 2] / 360.0) * ShouXingUtil.PI_2) * 36525
        return JulianDay.fromJulianDay(jd + t - dtT(t)).getSolarTime()
    }

    /**
     * 公历时刻
     *
     * @return 公历时刻
     */
    fun getSolarTime(): SolarTime {
        val t = getStartSolarTime()
        return if (getIndex() % 2 == 1) t.next(1) else t
    }

    /**
     * 公历日
     *
     * @return 公历日
     */
    fun getSolarDay(): SolarDay {
        val d = getStartSolarTime().getSolarDay()
        return if (getIndex() % 2 == 1) d.next(1) else d
    }

    companion object {
        val NAMES: Array<String> = arrayOf("新月", "蛾眉月", "上弦月", "盈凸月", "满月", "亏凸月", "下弦月", "残月")

        @JvmStatic
        fun fromIndex(lunarYear: Int, lunarMonth: Int, index: Int): Phase {
            return Phase(lunarYear, lunarMonth, index)
        }

        @JvmStatic
        fun fromName(lunarYear: Int, lunarMonth: Int, name: String): Phase {
            return Phase(lunarYear, lunarMonth, name)
        }
    }
}

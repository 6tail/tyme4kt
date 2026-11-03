package com.tyme.culture

import com.tyme.LoopTyme
import com.tyme.jd.JulianDay
import com.tyme.lunar.LunarDay
import com.tyme.lunar.LunarMonth
import com.tyme.solar.SolarDay
import com.tyme.solar.SolarTime
import com.tyme.util.ShouXingUtil
import kotlin.jvm.JvmStatic
import kotlin.math.floor

/**
 * 月相（月日黄经差：新月0，蛾眉月0-90，上弦月90，盈凸月90-180，满月180，亏凸月180-270，下弦月270，残月270-360）
 *
 * @author 6tail
 */
class Phase: LoopTyme {
    private var lunarYear: Int
    private var lunarMonth: Int

    constructor(lunarYear: Int, lunarMonth: Int, index: Int): super(NAMES, index) {
        val m: LunarMonth = LunarMonth(lunarYear, lunarMonth).next(index / getSize())
        this.lunarYear = m.year
        this.lunarMonth = m.monthValue
    }

    constructor(lunarYear: Int, lunarMonth: Int, name: String): super(NAMES, name) {
        this.lunarYear = lunarYear
        this.lunarMonth = lunarMonth
    }

    override fun next(n: Int): Phase {
        val size: Int = getSize()
        var i: Int = index + n
        if (i < 0) {
            i -= size
        }
        i /= size
        var m: LunarMonth = LunarMonth(lunarYear, lunarMonth)
        if (i != 0) {
            m = m.next(i)
        }
        return Phase(m.year, m.monthValue, nextIndex(n))
    }

    private fun getStartSolarTime(): SolarTime {
        val n: Int = floor((lunarYear - 2000) * 365.2422 / 29.53058886).toInt()
        var i = 0
        val d: SolarDay = LunarDay(lunarYear, lunarMonth, 1).getSolarDay()
        val jd: Double = JulianDay.J2000 + ShouXingUtil.ONE_THIRD
        while (true) {
            val t: Double = ShouXingUtil.msaLonT((n + i) * ShouXingUtil.PI_2) * 36525
            if (!JulianDay(jd + t - ShouXingUtil.dtT(t)).getSolarDay().isBefore(d)) {
                break
            }
            i++
        }
        val t: Double = ShouXingUtil.msaLonT((n + i + intArrayOf(0, 90, 180, 270)[index / 2] / 360.0) * ShouXingUtil.PI_2) * 36525
        return JulianDay(jd + t - ShouXingUtil.dtT(t)).getSolarTime()
    }

    /**
     * 公历时刻
     *
     * @return 公历时刻
     */
    fun getSolarTime(): SolarTime {
        val t: SolarTime = getStartSolarTime()
        return if (index.and(1) == 1) t.next(1) else t
    }

    /**
     * 公历日
     *
     * @return 公历日
     */
    fun getSolarDay(): SolarDay {
        val d: SolarDay = getStartSolarTime().solarDay
        return if (index.and(1) == 1) d.next(1) else d
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

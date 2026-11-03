package com.tyme.solar

import com.tyme.LoopTyme
import com.tyme.jd.JulianDay
import com.tyme.util.ShouXingUtil
import kotlin.jvm.JvmStatic
import kotlin.math.floor

/**
 * 节气
 *
 * @author 6tail
 */
class SolarTerm: LoopTyme {
    /**
     * 年
     */
    var year: Int = 0
        private set

    /**
     * 儒略日（用于日历，只精确到日中午12:00）
     */
    var cursoryJulianDay: Double = 0.0
        private set

    constructor(year: Int, index: Int): super(NAMES, index) {
        val size = getSize()
        initByYear((year * size + index) / size, super.index)
    }

    constructor(year: Int, name: String): super(NAMES, name) {
        initByYear(year, super.index)
    }

    private fun initByYear(year: Int, offset: Int) {
        val jd = floor((year - 2000) * 365.2422 + 180)
        // 355是2000.12冬至，得到较靠近jd的冬至估计值
        var w: Double = floor((jd - 355 + 183) / 365.2422) * 365.2422 + 355
        if (ShouXingUtil.calcQi(w) > jd) {
            w -= 365.2422
        }
        this.year = year
        cursoryJulianDay = ShouXingUtil.calcQi(w + 15.2184 * offset)
    }

    override fun next(n: Int): SolarTerm {
        val size = getSize()
        val i = index + n
        return SolarTerm((year * size + i) / size, indexOf(i))
    }

    /**
     * 是否节令
     *
     * @return true/false
     */
    fun isJie(): Boolean {
        return index.and(1) == 1
    }

    /**
     * 是否气令
     *
     * @return true/false
     */
    fun isQi(): Boolean {
        return index.and(1) == 0
    }

    /**
     * 儒略日（精确到秒）
     *
     * @return 儒略日
     */
    fun getJulianDay(): JulianDay {
        return JulianDay(ShouXingUtil.qiAccurate2(cursoryJulianDay) + JulianDay.J2000)
    }

    /**
     * 公历日（用于日历）
     *
     * @return 公历日
     */
    fun getSolarDay(): SolarDay {
        return JulianDay(cursoryJulianDay + JulianDay.J2000).getSolarDay()
    }

    companion object {
        val NAMES: Array<String> = arrayOf("冬至", "小寒", "大寒", "立春", "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至", "小暑", "大暑", "立秋", "处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪")

        @JvmStatic
        fun fromIndex(year: Int, index: Int): SolarTerm {
            return SolarTerm(year, index)
        }

        @JvmStatic
        fun fromName(year: Int, name: String): SolarTerm {
            return SolarTerm(year, name)
        }
    }
}

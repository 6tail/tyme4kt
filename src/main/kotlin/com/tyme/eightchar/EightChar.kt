package com.tyme.eightchar

import com.tyme.AbstractCulture
import com.tyme.sixtycycle.EarthBranch
import com.tyme.sixtycycle.HeavenStem
import com.tyme.sixtycycle.SixtyCycle
import com.tyme.solar.SolarDay
import com.tyme.solar.SolarTerm
import com.tyme.solar.SolarTime
import kotlin.math.ceil

/**
 * 八字
 *
 * @author 6tail
 */
class EightChar: AbstractCulture {
    /** 年柱 */
    private var year: SixtyCycle
    /** 月柱 */
    private var month: SixtyCycle
    /** 日柱 */
    private var day: SixtyCycle
    /** 时柱 */
    private var hour: SixtyCycle

    /**
     * 初始化
     *
     * @param year  年柱
     * @param month 月柱
     * @param day   日柱
     * @param hour  时柱
     */
    constructor(year: String, month: String, day: String, hour: String): super() {
        this.year = SixtyCycle(year)
        this.month = SixtyCycle(month)
        this.day = SixtyCycle(day)
        this.hour = SixtyCycle(hour)
    }

    constructor(year: SixtyCycle, month: SixtyCycle, day: SixtyCycle, hour: SixtyCycle): super() {
        this.year = year
        this.month = month
        this.day = day
        this.hour = hour
    }

    /**
     * 年柱
     *
     * @return 年柱
     */
    fun getYear(): SixtyCycle {
        return year
    }

    /**
     * 月柱
     *
     * @return 月柱
     */
    fun getMonth(): SixtyCycle {
        return month
    }

    /**
     * 日柱
     *
     * @return 日柱
     */
    fun getDay(): SixtyCycle {
        return day
    }

    /**
     * 时柱
     *
     * @return 时柱
     */
    fun getHour(): SixtyCycle {
        return hour
    }

    /**
     * 胎元
     *
     * @return 胎元
     */
    fun getFetalOrigin(): SixtyCycle {
        return SixtyCycle(month.getHeavenStem().next(1).getName() + month.getEarthBranch().next(3).getName())
    }

    /**
     * 胎息
     *
     * @return 胎息
     */
    fun getFetalBreath(): SixtyCycle {
        return SixtyCycle(day.getHeavenStem().next(5).getName() + EarthBranch(13 - day.getEarthBranch().getIndex()).getName())
    }

    /**
     * 命宫
     *
     * @return 命宫
     */
    fun getOwnSign(): SixtyCycle {
        var m: Int = month.getEarthBranch().getIndex() - 1
        if (m < 1) {
            m += 12
        }
        var h: Int = hour.getEarthBranch().getIndex() - 1
        if (h < 1) {
            h += 12
        }
        var offset: Int = m + h
        offset = (if (offset >= 14) 26 else 14) - offset
        return SixtyCycle(HeavenStem((year.getHeavenStem().getIndex() + 1) * 2 + offset - 1).getName() + EarthBranch(offset + 1).getName())
    }

    /**
     * 身宫
     *
     * @return 身宫
     */
    fun getBodySign(): SixtyCycle {
        var offset: Int = month.getEarthBranch().getIndex() - 1
        if (offset < 1) {
            offset += 12
        }
        offset += hour.getEarthBranch().getIndex() + 1
        if (offset > 12) {
            offset -= 12
        }
        return SixtyCycle(HeavenStem((year.getHeavenStem().getIndex() + 1) * 2 + offset - 1).getName() + EarthBranch(offset + 1).getName())
    }

    /**
     * 公历时刻列表
     *
     * @param startYear 开始年(含)，支持1-9999年
     * @param endYear   结束年(含)，支持1-9999年
     * @return 公历时刻列表
     */
    fun getSolarTimes(startYear: Int, endYear: Int): List<SolarTime> {
        val l: MutableList<SolarTime> = ArrayList()
        // 月地支距寅月的偏移值
        var m: Int = month.getEarthBranch().next(-2).getIndex()
        // 月天干要一致
        if (HeavenStem((year.getHeavenStem().getIndex() + 1) * 2 + m) != month.getHeavenStem()) {
            return l
        }
        // 1年的立春是辛酉，序号57
        var y: Int = year.next(-57).getIndex() + 1
        // 节令偏移值
        m *= 2
        // 时辰地支转时刻
        val h: Int = hour.getEarthBranch().getIndex() * 2
        // 兼容子时多流派
        val hours: IntArray = if (h == 0) intArrayOf(0, 23) else intArrayOf(h)
        val baseYear: Int = startYear - 1
        if (baseYear > y) {
            y += 60 * ceil((baseYear - y) / 60.0).toInt()
        }
        while (y <= endYear) {
            // 立春为寅月的开始
            var term = SolarTerm(y, 3)
            // 节令推移，年干支和月干支就都匹配上了
            if (m > 0) {
                term = term.next(m)
            }
            val solarTime: SolarTime = term.getJulianDay().getSolarTime()
            if (solarTime.getYear() >= startYear) {
                // 日干支和节令干支的偏移值
                var solarDay: SolarDay = solarTime.getSolarDay()
                val d: Int = day.next(-solarDay.getLunarDay().getSixtyCycle().getIndex()).getIndex()
                if (d > 0) {
                    // 从节令推移天数
                    solarDay = solarDay.next(d)
                }
                for (hour: Int in hours) {
                    var mi = 0
                    var s = 0
                    // 如果正好是节令当天，且小时和节令的小时数相等的极端情况，把分钟和秒钟带上
                    if (d == 0 && hour == solarTime.getHour()) {
                        mi = solarTime.getMinute()
                        s = solarTime.getSecond()
                    }
                    val time = SolarTime(solarDay.getYear(), solarDay.getMonth(), solarDay.getDay(), hour, mi, s)
                    // 验证一下
                    if (time.getLunarHour().getEightChar() == this) {
                        l.add(time)
                    }
                }
            }
            y += 60
        }
        return l
    }

    override fun getName(): String {
        return "$year $month $day $hour"
    }

    override fun equals(other: Any?): Boolean {
        return other is EightChar && toString() == other.toString()
    }

    override fun hashCode(): Int {
        return getName().hashCode()
    }

}

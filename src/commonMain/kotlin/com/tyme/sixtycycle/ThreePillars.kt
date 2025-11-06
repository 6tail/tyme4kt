package com.tyme.sixtycycle

import com.tyme.AbstractCulture
import com.tyme.solar.SolarDay
import com.tyme.solar.SolarTerm
import kotlin.math.ceil

/**
 * 三柱（年柱、月柱、日柱）
 *
 * @author 6tail
 */
class ThreePillars: AbstractCulture {
    /** 年柱 */
    private var year: SixtyCycle
    /** 月柱 */
    private var month: SixtyCycle
    /** 日柱 */
    private var day: SixtyCycle

    /**
     * 初始化
     *
     * @param year  年柱
     * @param month 月柱
     * @param day   日柱
     */
    constructor(year: String, month: String, day: String): super() {
        this.year = SixtyCycle(year)
        this.month = SixtyCycle(month)
        this.day = SixtyCycle(day)
    }

    constructor(year: SixtyCycle, month: SixtyCycle, day: SixtyCycle): super() {
        this.year = year
        this.month = month
        this.day = day
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
     * 公历日列表
     *
     * @param startYear 开始年(含)，支持1-9999年
     * @param endYear   结束年(含)，支持1-9999年
     * @return 公历日列表
     */
    fun getSolarDays(startYear: Int, endYear: Int): List<SolarDay> {
        val l: MutableList<SolarDay> = ArrayList()
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
            var solarDay: SolarDay = term.getSolarDay()
            if (solarDay.getYear() >= startYear) {
                // 日干支和节令干支的偏移值
                val d: Int = day.next(-solarDay.getLunarDay().getSixtyCycle().getIndex()).getIndex()
                if (d > 0) {
                    // 从节令推移天数
                    solarDay = solarDay.next(d)
                }
                // 验证一下
                if (solarDay.getSixtyCycleDay().getThreePillars() == this) {
                    l.add(solarDay)
                }
            }
            y += 60
        }
        return l
    }

    override fun getName(): String {
        return "$year $month $day"
    }

    override fun equals(other: Any?): Boolean {
        return other is ThreePillars && toString() == other.toString()
    }

    override fun hashCode(): Int {
        return getName().hashCode()
    }

}

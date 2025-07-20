package com.tyme.eightchar.provider.impl

import com.tyme.eightchar.ChildLimitInfo
import com.tyme.solar.SolarTerm
import com.tyme.solar.SolarTime

/**
 * Lunar的流派1童限计算（按天数和时辰数计算，3天1年，1天4个月，1时辰10天）
 *
 * @author 6tail
 */
class LunarSect1ChildLimitProvider: AbstractChildLimitProvider() {
    override fun getInfo(birthTime: SolarTime, term: SolarTerm): ChildLimitInfo {
        val termTime: SolarTime = term.getJulianDay().getSolarTime()
        var end: SolarTime = termTime
        var start: SolarTime = birthTime
        if (birthTime.isAfter(termTime)) {
            end = birthTime
            start = termTime
        }
        val endTimeZhiIndex: Int = if ((end.getHour() == 23)) 11 else end.getLunarHour().getIndexInDay()
        val startTimeZhiIndex: Int = if ((start.getHour() == 23)) 11 else start.getLunarHour().getIndexInDay()
        // 时辰差
        var hourDiff: Int = endTimeZhiIndex - startTimeZhiIndex
        // 天数差
        var dayDiff: Int = end.getSolarDay().subtract(start.getSolarDay())
        if (hourDiff < 0) {
            hourDiff += 12
            dayDiff--
        }
        val monthDiff: Int = hourDiff * 10 / 30
        var month: Int = dayDiff * 4 + monthDiff
        val day: Int = hourDiff * 10 - monthDiff * 30
        val year: Int = month / 12
        month -= year * 12

        return next(birthTime, year, month, day, 0, 0, 0)
    }
}

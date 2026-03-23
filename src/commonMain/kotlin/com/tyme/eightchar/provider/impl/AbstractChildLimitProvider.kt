package com.tyme.eightchar.provider.impl

import com.tyme.eightchar.ChildLimitInfo
import com.tyme.eightchar.provider.ChildLimitProvider
import com.tyme.solar.SolarMonth
import com.tyme.solar.SolarTerm
import com.tyme.solar.SolarTime

/**
 * 童限计算抽象
 *
 * @author 6tail
 */
abstract class AbstractChildLimitProvider: ChildLimitProvider {
    fun next(birthTime: SolarTime, addYear: Int, addMonth: Int, addDay: Int, addHour: Int, addMinute: Int, addSecond: Int): ChildLimitInfo {
        var d: Int = birthTime.day + addDay
        var h: Int = birthTime.hour + addHour
        var mi: Int = birthTime.minute + addMinute
        var s: Int = birthTime.second + addSecond
        mi += s / 60
        s %= 60
        h += mi / 60
        mi %= 60
        d += h / 24
        h %= 24

        var sm: SolarMonth = SolarMonth(birthTime.year + addYear, birthTime.month).next(addMonth)

        var dc: Int = sm.getDayCount()
        while (d > dc) {
            d -= dc
            sm = sm.next(1)
            dc = sm.getDayCount()
        }
        return ChildLimitInfo(birthTime, SolarTime(sm.year, sm.month, d, h, mi, s), addYear, addMonth, addDay, addHour, addMinute)
    }

    abstract override fun getInfo(birthTime: SolarTime, term: SolarTerm): ChildLimitInfo
}

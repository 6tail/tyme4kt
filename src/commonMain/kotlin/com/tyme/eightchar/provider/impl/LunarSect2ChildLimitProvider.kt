package com.tyme.eightchar.provider.impl

import com.tyme.eightchar.ChildLimitInfo
import com.tyme.solar.SolarTerm
import com.tyme.solar.SolarTime
import com.tyme.unit.SecondUnit
import kotlin.math.abs

/**
 * Lunar的流派2童限计算（按分钟数计算）
 *
 * @author 6tail
 */
open class LunarSect2ChildLimitProvider: AbstractChildLimitProvider() {
    protected fun compute(birthTime: SolarTime, term: SolarTerm): SecondUnit {
        // 出生时刻和节令时刻相差的分钟数
        var minutes = abs(term.getJulianDay().getSolarTime().subtract(birthTime)) / 60
        val year = minutes / 4320
        minutes %= 4320
        val month = minutes / 360
        minutes %= 360
        val day = minutes / 12
        minutes %= 12
        val hour = minutes * 2
        return SecondUnit(year, month, day, hour, 0, 0)
    }

    override fun getInfo(birthTime: SolarTime, term: SolarTerm): ChildLimitInfo {
        val t = compute(birthTime, term)
        return next(birthTime, t.year, t.month, t.day, t.hour, 0, 0)
    }
}

package com.tyme.eightchar.provider.impl

import com.tyme.eightchar.ChildLimitInfo
import com.tyme.solar.SolarTerm
import com.tyme.solar.SolarTime
import kotlin.math.abs

/**
 * Lunar的流派2童限计算（按分钟数计算）
 *
 * @author 6tail
 */
class LunarSect2ChildLimitProvider: AbstractChildLimitProvider() {
    override fun getInfo(birthTime: SolarTime, term: SolarTerm): ChildLimitInfo {
        // 出生时刻和节令时刻相差的分钟数
        var minutes = abs(term.getJulianDay().getSolarTime().subtract(birthTime)) / 60
        val year = minutes / 4320
        minutes %= 4320
        val month = minutes / 360
        minutes %= 360
        val day = minutes / 12
        minutes %= 12
        val hour = minutes * 2

        return next(birthTime, year, month, day, hour, 0, 0)
    }
}

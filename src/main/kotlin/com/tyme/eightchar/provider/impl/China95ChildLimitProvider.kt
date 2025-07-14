package com.tyme.eightchar.provider.impl

import com.tyme.eightchar.ChildLimitInfo
import com.tyme.solar.SolarTerm
import com.tyme.solar.SolarTime
import kotlin.math.abs

/**
 * 元亨利贞的童限计算
 *
 * @author 6tail
 */
class China95ChildLimitProvider : AbstractChildLimitProvider() {
    override fun getInfo(birthTime: SolarTime, term: SolarTerm): ChildLimitInfo {
        // 出生时刻和节令时刻相差的分钟数
        var minutes = abs(term.getJulianDay().getSolarTime().subtract(birthTime)) / 60
        val year = minutes / 4320
        minutes %= 4320
        val month = minutes / 360
        minutes %= 360
        val day = minutes / 12

        return this.next(birthTime, year, month, day, 0, 0, 0)
    }
}

package com.tyme.eightchar.provider.impl

import com.tyme.eightchar.ChildLimitInfo
import com.tyme.solar.SolarTerm
import com.tyme.solar.SolarTime

/**
 * 元亨利贞的童限计算
 *
 * @author 6tail
 */
class China95ChildLimitProvider: LunarSect2ChildLimitProvider() {
    override fun getInfo(birthTime: SolarTime, term: SolarTerm): ChildLimitInfo {
        val t = compute(birthTime, term)
        return next(birthTime, t.year, t.month, t.day, 0, 0, 0)
    }
}

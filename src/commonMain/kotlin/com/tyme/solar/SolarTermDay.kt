package com.tyme.solar

import com.tyme.AbstractCultureDay

/**
 * 节气第几天
 *
 * @author 6tail
 */
class SolarTermDay(solarTerm: SolarTerm, dayIndex: Int) : AbstractCultureDay(solarTerm, dayIndex) {
    /**
     * 节气
     *
     * @return 节气
     */
    fun getSolarTerm(): SolarTerm {
        return culture as SolarTerm
    }
}

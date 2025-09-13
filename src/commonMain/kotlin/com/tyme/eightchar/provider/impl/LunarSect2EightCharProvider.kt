package com.tyme.eightchar.provider.impl

import com.tyme.eightchar.EightChar
import com.tyme.eightchar.provider.EightCharProvider
import com.tyme.lunar.LunarHour
import com.tyme.sixtycycle.SixtyCycleHour

/**
 * Lunar流派2的八字计算（晚子时日柱算当天）
 *
 * @author 6tail
 */
class LunarSect2EightCharProvider: EightCharProvider {
    override fun getEightChar(hour: LunarHour): EightChar {
        val h: SixtyCycleHour = hour.getSixtyCycleHour()
        return EightChar(h.getYear(), h.getMonth(), hour.getLunarDay().getSixtyCycle(), h.getSixtyCycle())
    }
}

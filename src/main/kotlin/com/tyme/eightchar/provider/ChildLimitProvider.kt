package com.tyme.eightchar.provider

import com.tyme.eightchar.ChildLimitInfo
import com.tyme.solar.SolarTerm
import com.tyme.solar.SolarTime

/**
 * 童限计算接口
 *
 * @author 6tail
 */
interface ChildLimitProvider {
    /**
     * 童限信息
     *
     * @param birthTime 出生公历时刻
     * @param term      节令
     * @return 童限信息
     */
    fun getInfo(birthTime: SolarTime, term: SolarTerm): ChildLimitInfo
}

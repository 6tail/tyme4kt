package com.tyme.test

import com.tyme.solar.SolarDay
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * 三柱测试
 *
 * @author 6tail
 */
class ThreePillarsTest {
    @Test
    fun test1(){
        assertEquals("甲戌 甲戌 甲戌", SolarDay.fromYmd(1034, 10, 2).getSixtyCycleDay().getThreePillars().getName())
    }
}

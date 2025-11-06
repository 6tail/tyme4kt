package com.tyme.test

import com.tyme.solar.SolarTerm.Companion.fromName
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
        assertEquals("1034年10月1日", fromName(1034, "寒露").getSolarDay().toString())
        assertEquals("1034年10月3日", fromName(1034, "寒露").getJulianDay().getSolarDay().toString())
        assertEquals("1034年10月3日 06:02:28", fromName(1034, "寒露").getJulianDay().getSolarTime().toString())
    }
}

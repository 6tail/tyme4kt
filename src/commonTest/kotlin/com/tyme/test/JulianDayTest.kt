package com.tyme.test

import com.tyme.solar.SolarDay
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * 儒略日测试
 *
 * @author 6tail
 */
class JulianDayTest {
    @Test
    fun test0() {
        assertEquals("2023年1月1日", SolarDay(2023, 1, 1).getJulianDay().getSolarDay().toString())
    }
}

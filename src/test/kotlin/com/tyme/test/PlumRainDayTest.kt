package com.tyme.test

import com.tyme.culture.plumrain.PlumRainDay
import com.tyme.solar.SolarDay
import kotlin.test.*

/**
 * 梅雨天测试
 *
 * @author 6tail
 */
class PlumRainDayTest {
    @Test
    fun test0() {
        val d: PlumRainDay? = SolarDay(2024, 6, 10).getPlumRainDay()
        assertNull(d)
    }

    @Test
    fun test1() {
        val d: PlumRainDay? = SolarDay(2024, 6, 11).getPlumRainDay()
        assertEquals("入梅", d?.getName())
        assertEquals("入梅", d?.getPlumRain().toString())
        assertEquals("入梅第1天", d.toString())
    }

    @Test
    fun test2() {
        val d: PlumRainDay? = SolarDay(2024, 7, 6).getPlumRainDay()
        assertEquals("出梅", d?.getName())
        assertEquals("出梅", d?.getPlumRain().toString())
        assertEquals("出梅", d.toString())
    }

    @Test
    fun test3() {
        val d: PlumRainDay? = SolarDay(2024, 7, 5).getPlumRainDay()
        assertEquals("入梅", d?.getName())
        assertEquals("入梅", d?.getPlumRain().toString())
        assertEquals("入梅第25天", d.toString())
    }
}

package com.tyme.test

import com.tyme.solar.SolarMonth
import kotlin.test.*

/**
 * 公历月测试
 *
 * @author 6tail
 */
class SolarMonthTest {
    @Test
    fun test0() {
        val m = SolarMonth(2019, 5)
        assertEquals("5月", m.getName())
        assertEquals("2019年5月", m.toString())
    }

    @Test
    fun test1() {
        val m = SolarMonth(2023, 1)
        assertEquals(5, m.getWeekCount(0).toLong())
        assertEquals(6, m.getWeekCount(1).toLong())
        assertEquals(6, m.getWeekCount(2).toLong())
        assertEquals(5, m.getWeekCount(3).toLong())
        assertEquals(5, m.getWeekCount(4).toLong())
        assertEquals(5, m.getWeekCount(5).toLong())
        assertEquals(5, m.getWeekCount(6).toLong())
    }

    @Test
    fun test2() {
        val m = SolarMonth(2023, 2)
        assertEquals(5, m.getWeekCount(0).toLong())
        assertEquals(5, m.getWeekCount(1).toLong())
        assertEquals(5, m.getWeekCount(2).toLong())
        assertEquals(4, m.getWeekCount(3).toLong())
        assertEquals(5, m.getWeekCount(4).toLong())
        assertEquals(5, m.getWeekCount(5).toLong())
        assertEquals(5, m.getWeekCount(6).toLong())
    }

    @Test
    fun test3() {
        val m = SolarMonth(2023, 10).next(1)
        assertEquals("11月", m.getName())
        assertEquals("2023年11月", m.toString())
    }

    @Test
    fun test4() {
        val m = SolarMonth(2023, 10)
        assertEquals("2023年12月", m.next(2).toString())
        assertEquals("2024年1月", m.next(3).toString())
        assertEquals("2023年5月", m.next(-5).toString())
        assertEquals("2023年1月", m.next(-9).toString())
        assertEquals("2022年12月", m.next(-10).toString())
        assertEquals("2025年10月", m.next(24).toString())
        assertEquals("2021年10月", m.next(-24).toString())
    }
}

package com.tyme.test

import com.tyme.solar.SolarHalfYear
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * 公历半年测试
 *
 * @author 6tail
 */
class SolarHalfYearTest {
    @Test
    fun test0() {
        assertEquals("上半年", SolarHalfYear(2023, 0).getName())
        assertEquals("2023年上半年", SolarHalfYear(2023, 0).toString())
    }

    @Test
    fun test1() {
        assertEquals("下半年", SolarHalfYear(2023, 1).getName())
        assertEquals("2023年下半年", SolarHalfYear(2023, 1).toString())
    }

    @Test
    fun test2() {
        assertEquals("下半年", SolarHalfYear(2023, 0).next(1).getName())
        assertEquals("2023年下半年", SolarHalfYear(2023, 0).next(1).toString())
    }

    @Test
    fun test3() {
        assertEquals("上半年", SolarHalfYear(2023, 0).next(2).getName())
        assertEquals("2024年上半年", SolarHalfYear(2023, 0).next(2).toString())
    }

    @Test
    fun test4() {
        assertEquals("上半年", SolarHalfYear(2023, 0).next(-2).getName())
        assertEquals("2022年上半年", SolarHalfYear(2023, 0).next(-2).toString())
    }

    @Test
    fun test5() {
        assertEquals("2021年上半年", SolarHalfYear(2023, 0).next(-4).toString())
        assertEquals("2021年下半年", SolarHalfYear(2023, 0).next(-3).toString())
    }
}

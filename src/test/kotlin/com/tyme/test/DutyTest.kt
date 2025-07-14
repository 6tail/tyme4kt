package com.tyme.test

import com.tyme.solar.SolarDay
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

/**
 * 建除十二值神测试
 *
 * @author 6tail
 */
class DutyTest {
    @Test
    fun test0() {
        assertEquals("闭", SolarDay(2023, 10, 30).getLunarDay().getDuty().getName())
        assertEquals("闭", SolarDay(2023, 10, 30).getSixtyCycleDay().getDuty().getName())
    }

    @Test
    fun test1() {
        assertEquals("建", SolarDay(2023, 10, 19).getLunarDay().getDuty().getName())
        assertEquals("建", SolarDay(2023, 10, 19).getSixtyCycleDay().getDuty().getName())
    }

    @Test
    fun test2() {
        assertEquals("除", SolarDay(2023, 10, 7).getLunarDay().getDuty().getName())
        assertEquals("除", SolarDay(2023, 10, 7).getSixtyCycleDay().getDuty().getName())
    }

    @Test
    fun test3() {
        assertEquals("除", SolarDay(2023, 10, 8).getLunarDay().getDuty().getName())
        assertEquals("除", SolarDay(2023, 10, 8).getSixtyCycleDay().getDuty().getName())
    }
}

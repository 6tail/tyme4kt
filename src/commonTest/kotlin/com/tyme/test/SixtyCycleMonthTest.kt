package com.tyme.test

import com.tyme.sixtycycle.SixtyCycleMonth
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * 干支月测试
 *
 * @author 6tail
 */
class SixtyCycleMonthTest {
    @Test
    fun test23() {
        val month = SixtyCycleMonth.fromIndex(2025, 0)
        assertEquals("乙巳年戊寅月", month.toString())
        assertEquals("乙巳年己卯月", month.next(1).toString())
        assertEquals("甲辰年丁丑月", month.next(-1).toString())
    }

    @Test
    fun test1() {
        val month = SixtyCycleMonth.fromIndex(1150, 0)
        assertEquals("庚午年戊寅月", month.toString())
        assertEquals("庚午年戊寅月戊寅日", month.firstDay.toString())
    }
}
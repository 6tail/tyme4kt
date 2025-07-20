package com.tyme.test

import com.tyme.sixtycycle.SixtyCycleDay
import com.tyme.solar.SolarDay
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * 干支日测试
 *
 * @author 6tail
 */
class SixtyCycleDayTest{
    @Test
    fun test0() {
       assertEquals("乙巳年戊寅月癸卯日", SixtyCycleDay(SolarDay(2025, 2, 3)).toString())
    }

    @Test
    fun test1() {
        assertEquals("甲辰年丁丑月壬寅日", SixtyCycleDay(SolarDay(2025, 2, 2)).toString())
    }
}
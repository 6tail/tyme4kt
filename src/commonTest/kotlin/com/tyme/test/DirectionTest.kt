package com.tyme.test

import com.tyme.solar.SolarDay
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * 方位测试
 *
 * @author 6tail
 */
class DirectionTest {
    /**
     * 福神方位
     */
    @Test
    fun test1() {
        assertEquals("东南", SolarDay(2021, 11, 13).getLunarDay().getSixtyCycle().getHeavenStem().getMascotDirection().getName())
    }

    /**
     * 福神方位
     */
    @Test
    fun test2() {
        assertEquals("东南", SolarDay(2024, 1, 1).getLunarDay().getSixtyCycle().getHeavenStem().getMascotDirection().getName())
    }

    /**
     * 太岁方位
     */
    @Test
    fun test3() {
        assertEquals("东", SolarDay(2023, 11, 6).getLunarDay().getJupiterDirection().getName())
    }
}

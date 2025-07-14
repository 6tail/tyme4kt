package com.tyme.test

import com.tyme.eightchar.EightChar
import com.tyme.sixtycycle.SixtyCycleHour
import com.tyme.solar.SolarTime
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

/**
 * 干支时辰测试
 *
 * @author 6tail
 */
class SixtyCycleHourTest{
    @Test
    fun test0() {
        val hour: SixtyCycleHour = SolarTime(2025, 2, 3, 23, 0, 0).getSixtyCycleHour()
        assertEquals("乙巳年戊寅月甲辰日甲子时", hour.toString())
        val day = hour.getSixtyCycleDay()
        assertEquals("乙巳年戊寅月甲辰日", day.toString())
        assertEquals("2025年2月3日", day.getSolarDay().toString())
    }

    @Test
    fun test1() {
        val hour: SixtyCycleHour = SolarTime(2025, 2, 3, 4, 0, 0).getSixtyCycleHour()
        assertEquals("甲辰年丁丑月癸卯日甲寅时", hour.toString())
        val day = hour.getSixtyCycleDay()
        assertEquals("甲辰年丁丑月癸卯日", day.toString())
        assertEquals("2025年2月3日", day.getSolarDay().toString())
    }

    @Test
    fun test2() {
        val hour: SixtyCycleHour = SolarTime(2025, 2, 3, 22, 30, 0).getSixtyCycleHour()
        assertEquals("乙巳年戊寅月癸卯日癸亥时", hour.toString())
        val day = hour.getSixtyCycleDay()
        assertEquals("乙巳年戊寅月癸卯日", day.toString())
        assertEquals("2025年2月3日", day.getSolarDay().toString())
    }

    @Test
    fun test3() {
        val eightChar: EightChar = SolarTime(1988, 2, 15, 23, 30, 0).getSixtyCycleHour().getEightChar()
        assertEquals("戊辰", eightChar.getYear().getName())
        assertEquals("甲寅", eightChar.getMonth().getName())
        assertEquals("辛丑", eightChar.getDay().getName())
        assertEquals("戊子", eightChar.getHour().getName())
    }
}
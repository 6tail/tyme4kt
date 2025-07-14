package com.tyme.test

import com.tyme.sixtycycle.HideHeavenStemDay
import com.tyme.solar.SolarDay
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

/**
 * 人元司令分野测试
 *
 * @author 6tail
 */
class HideHeavenStemDayTest {
    @Test
    fun test0() {
        val d: HideHeavenStemDay = SolarDay(2024, 12, 4).getHideHeavenStemDay()
        assertEquals("本气", d.getHideHeavenStem().getType().getName())
        assertEquals("壬", d.getHideHeavenStem().getName())
        assertEquals("壬", d.getHideHeavenStem().toString())
        assertEquals("水", d.getHideHeavenStem().getHeavenStem().getElement().getName())

        assertEquals("壬水", d.getName())
        assertEquals(15, d.getDayIndex().toLong())
        assertEquals("壬水第16天", d.toString())
    }

    @Test
    fun test1() {
        val d: HideHeavenStemDay = SolarDay(2024, 11, 7).getHideHeavenStemDay()
        assertEquals("余气", d.getHideHeavenStem().getType().getName())
        assertEquals("戊", d.getHideHeavenStem().getName())
        assertEquals("戊", d.getHideHeavenStem().toString())
        assertEquals("土", d.getHideHeavenStem().getHeavenStem().getElement().getName())

        assertEquals("戊土", d.getName())
        assertEquals(0, d.getDayIndex().toLong())
        assertEquals("戊土第1天", d.toString())
    }
}

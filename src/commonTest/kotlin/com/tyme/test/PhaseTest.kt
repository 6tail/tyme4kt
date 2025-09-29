package com.tyme.test

import com.tyme.culture.Phase
import com.tyme.lunar.LunarDay
import com.tyme.solar.SolarDay
import com.tyme.solar.SolarTime
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * 月相测试
 *
 * @author 6tail
 */
class PhaseTest {

    @Test
    fun test0() {
        val phase = Phase.fromName(2025, 7, "下弦月")
        assertEquals("2025年9月14日 18:32:57", phase.getSolarTime().toString())
    }

    @Test
    fun test1() {
        val phase = Phase.fromIndex(2025, 7, 6)
        assertEquals("2025年9月14日 18:32:57", phase.getSolarTime().toString())
    }

    @Test
    fun test2() {
        val phase = Phase.fromIndex(2025, 7, 8)
        assertEquals("2025年9月22日 03:54:07", phase.getSolarTime().toString())
    }

    @Test
    fun test3() {
        val phase = SolarDay.fromYmd(2025, 9, 21).getPhase()
        assertEquals("残月", phase.toString())
    }

    @Test
    fun test4() {
        val phase = LunarDay.fromYmd(2025, 7, 30).getPhase()
        assertEquals("残月", phase.toString())
    }

    @Test
    fun test5() {
        val phase = SolarTime.fromYmdHms(2025, 9, 22, 4, 0, 0).getPhase()
        assertEquals("蛾眉月", phase.toString())
    }

    @Test
    fun test6() {
        val phase = SolarTime.fromYmdHms(2025, 9, 22, 3, 0, 0).getPhase()
        assertEquals("残月", phase.toString())
    }

    @Test
    fun test7() {
        val d = SolarDay.fromYmd(2023, 9, 15).getPhaseDay()
        assertEquals("新月第1天", d.toString())
    }

    @Test
    fun test8() {
        val d = SolarDay.fromYmd(2023, 9, 17).getPhaseDay()
        assertEquals("蛾眉月第2天", d.toString())
    }

    @Test
    fun test9() {
        val phase = SolarTime.fromYmdHms(2025, 9, 22, 3, 54, 7).getPhase()
        assertEquals("新月", phase.toString())
    }

    @Test
    fun test10() {
        val phase = SolarTime.fromYmdHms(2025, 9, 22, 3, 54, 6).getPhase()
        assertEquals("残月", phase.toString())
    }

    @Test
    fun test11() {
        val phase = SolarTime.fromYmdHms(2025, 9, 22, 3, 54, 8).getPhase()
        assertEquals("蛾眉月", phase.toString())
    }
}

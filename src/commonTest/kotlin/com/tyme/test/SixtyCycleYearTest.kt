package com.tyme.test

import com.tyme.sixtycycle.SixtyCycleYear
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * 干支年测试
 *
 * @author 6tail
 */
class SixtyCycleYearTest {
    @Test
    fun test0() {
        assertEquals("癸卯年", SixtyCycleYear(2023).getName())
    }

    @Test
    fun test1() {
        assertEquals("戊申年", SixtyCycleYear(2023).next(5).getName())
    }

    @Test
    fun test2() {
        assertEquals("戊戌年", SixtyCycleYear(2023).next(-5).getName())
    }

    /**
     * 干支年的干支
     */
    @Test
    fun test3() {
        assertEquals("庚子", SixtyCycleYear(2020).getSixtyCycle().getName())
    }

    /**
     * 干支年的生肖(干支年.干支.地支.生肖)
     */
    @Test
    fun test4() {
        assertEquals("虎", SixtyCycleYear(1986).getSixtyCycle().getEarthBranch().getZodiac().getName())
    }

    @Test
    fun test5() {
        assertEquals("庚子年", SixtyCycleYear(2025).next(-5).getName())
    }

    @Test
    fun test7() {
        val y = SixtyCycleYear(2023)
        assertEquals("癸卯", y.getSixtyCycle().getName())
        assertEquals("兔", y.getSixtyCycle().getEarthBranch().getZodiac().getName())
    }

    @Test
    fun test8() {
        assertEquals("上元", SixtyCycleYear(1864).twenty.getSixty().getName())
    }

    @Test
    fun test9() {
        assertEquals("上元", SixtyCycleYear(1923).twenty.getSixty().getName())
    }

    @Test
    fun test10() {
        assertEquals("中元", SixtyCycleYear(1924).twenty.getSixty().getName())
    }

    @Test
    fun test11() {
        assertEquals("中元", SixtyCycleYear(1983).twenty.getSixty().getName())
    }

    @Test
    fun test12() {
        assertEquals("下元", SixtyCycleYear(1984).twenty.getSixty().getName())
    }

    @Test
    fun test13() {
        assertEquals("下元", SixtyCycleYear(2043).twenty.getSixty().getName())
    }

    @Test
    fun test14() {
        assertEquals("一运", SixtyCycleYear(1864).twenty.getName())
    }

    @Test
    fun test15() {
        assertEquals("一运", SixtyCycleYear(1883).twenty.getName())
    }

    @Test
    fun test16() {
        assertEquals("二运", SixtyCycleYear(1884).twenty.getName())
    }

    @Test
    fun test17() {
        assertEquals("二运", SixtyCycleYear(1903).twenty.getName())
    }

    @Test
    fun test18() {
        assertEquals("三运", SixtyCycleYear(1904).twenty.getName())
    }

    @Test
    fun test19() {
        assertEquals("三运", SixtyCycleYear(1923).twenty.getName())
    }

    @Test
    fun test20() {
        assertEquals("八运", SixtyCycleYear(2004).twenty.getName())
    }

    @Test
    fun test21() {
        val year = SixtyCycleYear(1)
        assertEquals("六运", year.twenty.getName())
        assertEquals("中元", year.twenty.getSixty().getName())
    }

    @Test
    fun test22() {
        val year = SixtyCycleYear(1863)
        assertEquals("九运", year.twenty.getName())
        assertEquals("下元", year.twenty.getSixty().getName())
    }

    @Test
    fun test23() {
        val year = SixtyCycleYear(2025)
        assertEquals("戊寅月", year.firstMonth.getName())
    }
}
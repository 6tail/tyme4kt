package com.tyme.test

import com.tyme.lunar.LunarDay
import com.tyme.lunar.LunarMonth
import com.tyme.solar.SolarDay
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

/**
 * 农历月测试
 *
 * @author 6tail
 */
class LunarMonthTest {
    @Test
    fun test0() {
        assertEquals("七月", LunarMonth.fromYm(2359, 7).getName())
    }

    /** 闰月 */
    @Test
    fun test1() {
        assertEquals("闰七月", LunarMonth.fromYm(2359, -7).getName())
    }

    @Test
    fun test2() {
        assertEquals(29, LunarMonth.fromYm(2023, 6).getDayCount().toLong())
    }

    @Test
    fun test3() {
        assertEquals(30, LunarMonth.fromYm(2023, 7).getDayCount().toLong())
    }

    @Test
    fun test4() {
        assertEquals(30, LunarMonth.fromYm(2023, 8).getDayCount().toLong())
    }

    @Test
    fun test5() {
        assertEquals(29, LunarMonth.fromYm(2023, 9).getDayCount().toLong())
    }

    @Test
    fun test6() {
        assertEquals("2023年10月15日", LunarMonth.fromYm(2023, 9).getFirstJulianDay().getSolarDay().toString())
    }

    @Test
    fun test7() {
        assertEquals("甲寅", LunarMonth.fromYm(2023, 1).getSixtyCycle().getName())
    }

    @Test
    fun test8() {
        assertEquals("丙辰", LunarMonth.fromYm(2023, -2).getSixtyCycle().getName())
    }

    @Test
    fun test9() {
        assertEquals("丁巳", LunarMonth.fromYm(2023, 3).getSixtyCycle().getName())
    }

    @Test
    fun test10() {
        assertEquals("丙寅", LunarMonth.fromYm(2024, 1).getSixtyCycle().getName())
    }

    @Test
    fun test11() {
        assertEquals("丙寅", LunarMonth.fromYm(2023, 12).getSixtyCycle().getName())
    }

    @Test
    fun test12() {
        assertEquals("壬寅", LunarMonth.fromYm(2022, 1).getSixtyCycle().getName())
    }

    @Test
    fun test13() {
        assertEquals("闰十二月", LunarMonth.fromYm(37, -12).getName())
    }

    @Test
    fun test14() {
        assertEquals("闰十二月", LunarMonth.fromYm(5552, -12).getName())
    }

    @Test
    fun test15() {
        assertEquals("农历戊子年十二月", LunarMonth.fromYm(2008, 11).next(1).toString())
    }

    @Test
    fun test16() {
        assertEquals("农历己丑年正月", LunarMonth.fromYm(2008, 11).next(2).toString())
    }

    @Test
    fun test17() {
        assertEquals("农历己丑年五月", LunarMonth.fromYm(2008, 11).next(6).toString())
    }

    @Test
    fun test18() {
        assertEquals("农历己丑年闰五月", LunarMonth.fromYm(2008, 11).next(7).toString())
    }

    @Test
    fun test19() {
        assertEquals("农历己丑年六月", LunarMonth.fromYm(2008, 11).next(8).toString())
    }

    @Test
    fun test20() {
        assertEquals("农历庚寅年正月", LunarMonth.fromYm(2008, 11).next(15).toString())
    }

    @Test
    fun test21() {
        assertEquals("农历戊子年十一月", LunarMonth.fromYm(2008, 12).next(-1).toString())
    }

    @Test
    fun test22() {
        assertEquals("农历戊子年十一月", LunarMonth.fromYm(2009, 1).next(-2).toString())
    }

    @Test
    fun test23() {
        assertEquals("农历戊子年十一月", LunarMonth.fromYm(2009, 5).next(-6).toString())
    }

    @Test
    fun test24() {
        assertEquals("农历戊子年十一月", LunarMonth.fromYm(2009, -5).next(-7).toString())
    }

    @Test
    fun test25() {
        assertEquals("农历戊子年十一月", LunarMonth.fromYm(2009, 6).next(-8).toString())
    }

    @Test
    fun test26() {
        assertEquals("农历戊子年十一月", LunarMonth.fromYm(2010, 1).next(-15).toString())
    }

    @Test
    fun test27() {
        assertEquals(29, LunarMonth.fromYm(2012, -4).getDayCount().toLong())
    }

    @Test
    fun test28() {
        assertEquals("癸亥", LunarMonth.fromYm(2023, 9).getSixtyCycle().toString())
    }

    @Test
    fun test29() {
        val solarDay = SolarDay(2023, 10, 7)
        val lunarDay = solarDay.getLunarDay()
        assertEquals("壬戌", lunarDay.getLunarMonth().getSixtyCycle().toString())
        assertEquals("辛酉", lunarDay.getSixtyCycleDay().getMonth().toString())
        assertEquals("辛酉", solarDay.getSixtyCycleDay().getMonth().toString())
    }

    @Test
    fun test30() {
        val solarDay = SolarDay(2023, 10, 8)
        val lunarDay = solarDay.getLunarDay()
        assertEquals("壬戌", lunarDay.getLunarMonth().getSixtyCycle().toString())
        assertEquals("壬戌", lunarDay.getSixtyCycleDay().getMonth().toString())
        assertEquals("壬戌", solarDay.getSixtyCycleDay().getMonth().toString())
    }

    @Test
    fun test31() {
        val solarDay = SolarDay(2023, 10, 15)
        val lunarDay = solarDay.getLunarDay()
        assertEquals("九月", lunarDay.getLunarMonth().getName())
        assertEquals("癸亥", lunarDay.getLunarMonth().getSixtyCycle().toString())
        assertEquals("壬戌", lunarDay.getSixtyCycleDay().getMonth().toString())
        assertEquals("壬戌", solarDay.getSixtyCycleDay().getMonth().toString())
    }

    @Test
    fun test32() {
        val solarDay = SolarDay(2023, 11, 7)
        val lunarDay = solarDay.getLunarDay()
        assertEquals("癸亥", lunarDay.getLunarMonth().getSixtyCycle().toString())
        assertEquals("壬戌", lunarDay.getSixtyCycleDay().getMonth().toString())
        assertEquals("壬戌", solarDay.getSixtyCycleDay().getMonth().toString())
    }

    @Test
    fun test33() {
        val solarDay = SolarDay(2023, 11, 8)
        val lunarDay = solarDay.getLunarDay()
        assertEquals("癸亥", lunarDay.getLunarMonth().getSixtyCycle().toString())
        assertEquals("癸亥", lunarDay.getSixtyCycleDay().getMonth().toString())
        assertEquals("癸亥", solarDay.getSixtyCycleDay().getMonth().toString())
    }

    @Test
    fun test34() {
        // 2023年闰2月
        val m = LunarMonth.fromYm(2023, 12)
        assertEquals("农历癸卯年十二月", m.toString())
        assertEquals("农历癸卯年十一月", m.next(-1).toString())
        assertEquals("农历癸卯年十月", m.next(-2).toString())
    }

    @Test
    fun test35() {
        // 2023年闰2月
        val m = LunarMonth.fromYm(2023, 3)
        assertEquals("农历癸卯年三月", m.toString())
        assertEquals("农历癸卯年闰二月", m.next(-1).toString())
        assertEquals("农历癸卯年二月", m.next(-2).toString())
        assertEquals("农历癸卯年正月", m.next(-3).toString())
        assertEquals("农历壬寅年十二月", m.next(-4).toString())
        assertEquals("农历壬寅年十一月", m.next(-5).toString())
    }

    @Test
    fun test36() {
        val solarDay = SolarDay(1983, 2, 15)
        val lunarDay = solarDay.getLunarDay()
        assertEquals("甲寅", lunarDay.getLunarMonth().getSixtyCycle().toString())
        assertEquals("甲寅", lunarDay.getSixtyCycleDay().getMonth().toString())
        assertEquals("甲寅", solarDay.getSixtyCycleDay().getMonth().toString())
    }

    @Test
    fun test37() {
        val solarDay = SolarDay(2023, 10, 30)
        val lunarDay = solarDay.getLunarDay()
        assertEquals("癸亥", lunarDay.getLunarMonth().getSixtyCycle().toString())
        assertEquals("壬戌", lunarDay.getSixtyCycleDay().getMonth().toString())
        assertEquals("壬戌", solarDay.getSixtyCycleDay().getMonth().toString())
    }

    @Test
    fun test38() {
        val solarDay = SolarDay(2023, 10, 19)
        val lunarDay = solarDay.getLunarDay()
        assertEquals("癸亥", lunarDay.getLunarMonth().getSixtyCycle().toString())
        assertEquals("壬戌", lunarDay.getSixtyCycleDay().getMonth().toString())
        assertEquals("壬戌", solarDay.getSixtyCycleDay().getMonth().toString())
    }

    @Test
    fun test39() {
        val m = LunarMonth.fromYm(2023, 11)
        assertEquals("农历癸卯年十一月", m.toString())
        assertEquals("乙丑", m.getSixtyCycle().toString())
    }

    @Test
    fun test40() {
        assertEquals("己未", LunarDay(2018, 6, 26).getLunarMonth().getSixtyCycle().toString())
        assertEquals("庚申", LunarDay(2018, 6, 26).getSixtyCycleDay().getMonth().toString())
    }

    @Test
    fun test41() {
        assertEquals("辛丑", LunarMonth.fromYm(1991, 12).getSixtyCycle().toString())
    }

    @Test
    fun test42() {
        assertEquals("速喜", LunarMonth.fromYm(1991, 3).getMinorRen().getName())
    }
}

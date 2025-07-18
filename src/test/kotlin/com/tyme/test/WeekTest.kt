package com.tyme.test

import com.tyme.lunar.LunarWeek
import com.tyme.solar.SolarDay
import com.tyme.solar.SolarWeek
import kotlin.test.*

/**
 * 星期测试
 *
 * @author 6tail
 */
class WeekTest{
    @Test
    fun test0() {
        assertEquals("一", SolarDay(1582, 10, 1).getWeek().getName())
    }

    @Test
    fun test1() {
        assertEquals("五", SolarDay(1582, 10, 15).getWeek().getName())
    }

    @Test
    fun test2() {
        assertEquals(2, SolarDay(2023, 10, 31).getWeek().getIndex())
    }

    @Test
    fun test3() {
        val w = SolarWeek(2023, 10, 0, 0)
        assertEquals("第一周", w.getName())
        assertEquals("2023年10月第一周", w.toString())
    }

    @Test
    fun test5() {
        val w = SolarWeek(2023, 10, 4, 0)
        assertEquals("第五周", w.getName())
        assertEquals("2023年10月第五周", w.toString())
    }

    @Test
    fun test6() {
        val w = SolarWeek(2023, 10, 5, 1)
        assertEquals("第六周", w.getName())
        assertEquals("2023年10月第六周", w.toString())
    }

    @Test
    fun test7() {
        val w = SolarWeek(2023, 10, 0, 0).next(4)
        assertEquals("第五周", w.getName())
        assertEquals("2023年10月第五周", w.toString())
    }

    @Test
    fun test8() {
        val w = SolarWeek(2023, 10, 0, 0).next(5)
        assertEquals("第二周", w.getName())
        assertEquals("2023年11月第二周", w.toString())
    }

    @Test
    fun test9() {
        val w = SolarWeek(2023, 10, 0, 0).next(-1)
        assertEquals("第五周", w.getName())
        assertEquals("2023年9月第五周", w.toString())
    }

    @Test
    fun test10() {
        val w = SolarWeek(2023, 10, 0, 0).next(-5)
        assertEquals("第一周", w.getName())
        assertEquals("2023年9月第一周", w.toString())
    }

    @Test
    fun test11() {
        val w = SolarWeek(2023, 10, 0, 0).next(-6)
        assertEquals("第四周", w.getName())
        assertEquals("2023年8月第四周", w.toString())
    }

    @Test
    fun test12() {
        val solar = SolarDay(1582, 10, 1)
        assertEquals(1, solar.getWeek().getIndex().toLong())
    }

    @Test
    fun test13() {
        val solar = SolarDay(1582, 10, 15)
        assertEquals(5, solar.getWeek().getIndex().toLong())
    }

    @Test
    fun test14() {
        val solar = SolarDay(1129, 11, 17)
        assertEquals(0, solar.getWeek().getIndex().toLong())
    }

    @Test
    fun test15() {
        val solar = SolarDay(1129, 11, 1)
        assertEquals(5, solar.getWeek().getIndex().toLong())
    }

    @Test
    fun test16() {
        val solar = SolarDay(8, 11, 1)
        assertEquals(4, solar.getWeek().getIndex().toLong())
    }

    @Test
    fun test17() {
        val solar = SolarDay(1582, 9, 30)
        assertEquals(0, solar.getWeek().getIndex().toLong())
    }

    @Test
    fun test18() {
        val solar = SolarDay(1582, 1, 1)
        assertEquals(1, solar.getWeek().getIndex().toLong())
    }

    @Test
    fun test19() {
        val solar = SolarDay(1500, 2, 29)
        assertEquals(6, solar.getWeek().getIndex().toLong())
    }

    @Test
    fun test20() {
        val solar = SolarDay(9865, 7, 26)
        assertEquals(3, solar.getWeek().getIndex().toLong())
    }

    @Test
    fun test21() {
        val week = LunarWeek(2023, 1, 0, 2)
        assertEquals("农历癸卯年正月第一周", week.toString())
        assertEquals("农历壬寅年十二月廿六", week.getFirstDay().toString())
    }

    @Test
    fun test22() {
        val week = SolarWeek(2023, 1, 0, 2)
        assertEquals("2023年1月第一周", week.toString())
        assertEquals("2022年12月27日", week.getFirstDay().toString())
    }

    @Test
    fun test24() {
        val start = 0
        var week = SolarWeek(2024, 2, 2, start)
        assertEquals("2024年2月第三周", week.toString())
        assertEquals(6, week.getIndexInYear().toLong())

        week = SolarDay(2024, 2, 11).getSolarWeek(start)
        assertEquals("2024年2月第三周", week.toString())

        week = SolarDay(2024, 2, 17).getSolarWeek(start)
        assertEquals("2024年2月第三周", week.toString())

        week = SolarDay(2024, 2, 10).getSolarWeek(start)
        assertEquals("2024年2月第二周", week.toString())

        week = SolarDay(2024, 2, 18).getSolarWeek(start)
        assertEquals("2024年2月第四周", week.toString())
    }

    @Test
    fun test25() {
        val week = LunarWeek(2024, 6, 0, 0)
        assertEquals("农历甲辰年六月第一周", week.toString())
        assertEquals("农历甲辰年六月第三周", week.next(2).toString())
        assertEquals("农历甲辰年七月第一周", week.next(5).toString())
        assertEquals("农历甲辰年五月第四周", week.next(-1).toString())
        assertEquals("农历甲辰年五月第一周", week.next(-4).toString())
    }

    @Test
    fun test26() {
        val week = SolarDay(2025, 3, 6).getSolarWeek(0)
        assertEquals("2025年3月第二周", week.toString())
        assertEquals("2025年3月2日", week.getFirstDay().toString())
    }
}

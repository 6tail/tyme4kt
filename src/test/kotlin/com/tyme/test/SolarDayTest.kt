package com.tyme.test

import com.tyme.lunar.LunarDay
import com.tyme.solar.SolarDay
import kotlin.test.*
/**
 * 公历日测试
 *
 * @author 6tail
 */
class SolarDayTest {
    @Test
    fun test0() {
        assertEquals("1日", SolarDay(2023, 1, 1).getName())
        assertEquals("2023年1月1日", SolarDay(2023, 1, 1).toString())
    }

    @Test
    fun test1() {
        assertEquals("29日", SolarDay(2000, 2, 29).getName())
        assertEquals("2000年2月29日", SolarDay(2000, 2, 29).toString())
    }

    @Test
    fun test2() {
        assertEquals(0, SolarDay(2023, 1, 1).getIndexInYear())
        assertEquals(364, SolarDay(2023, 12, 31).getIndexInYear())
        assertEquals(365, SolarDay(2020, 12, 31).getIndexInYear())
    }

    @Test
    fun test3() {
        assertEquals(0, SolarDay(2023, 1, 1).subtract(SolarDay(2023, 1, 1)))
        assertEquals(1, SolarDay(2023, 1, 2).subtract(SolarDay(2023, 1, 1)))
        assertEquals(-1, SolarDay(2023, 1, 1).subtract(SolarDay(2023, 1, 2)))
        assertEquals(31, SolarDay(2023, 2, 1).subtract(SolarDay(2023, 1, 1)))
        assertEquals(-31, SolarDay(2023, 1, 1).subtract(SolarDay(2023, 2, 1)))
        assertEquals(365, SolarDay(2024, 1, 1).subtract(SolarDay(2023, 1, 1)))
        assertEquals(-365, SolarDay(2023, 1, 1).subtract(SolarDay(2024, 1, 1)))
        assertEquals(1, SolarDay(1582, 10, 15).subtract(SolarDay(1582, 10, 4)))
    }

    @Test
    fun test4() {
        assertEquals("1582年10月4日", SolarDay(1582, 10, 15).next(-1).toString())
    }

    @Test
    fun test5() {
        assertEquals("2000年3月1日", SolarDay(2000, 2, 28).next(2).toString())
    }

    @Test
    fun test6() {
        assertEquals("农历庚子年闰四月初二", SolarDay(2020, 5, 24).getLunarDay().toString())
    }

    @Test
    fun test7() {
        assertEquals(31, SolarDay(2020, 5, 24).subtract(SolarDay(2020, 4, 23)))
    }

    @Test
    fun test8() {
        assertEquals("农历丙子年十一月十二", SolarDay(16, 11, 30).getLunarDay().toString())
    }

    @Test
    fun test9() {
        assertEquals("霜降", SolarDay(2023, 10, 27).getTerm().toString())
    }

    @Test
    fun test10() {
        assertEquals("豺乃祭兽第4天", SolarDay(2023, 10, 27).getPhenologyDay().toString())
    }

    @Test
    fun test11() {
        assertEquals("初候", SolarDay(2023, 10, 27).getPhenologyDay().getPhenology().getThreePhenology().toString())
    }

    @Test
    fun test22() {
        assertEquals("甲辰", SolarDay(2024, 2, 10).getLunarDay().getLunarMonth().getLunarYear().getSixtyCycle().getName())
    }

    @Test
    fun test23() {
        assertEquals("癸卯", SolarDay(2024, 2, 9).getLunarDay().getLunarMonth().getLunarYear().getSixtyCycle().getName())
    }

    @Test
    fun test24() {
        val prev: SolarDay = LunarDay(2025, 1, 1).getSolarDay()
        val next: SolarDay = LunarDay(2026, 1, 1).getSolarDay()
        val today = SolarDay(2025, 2, 17)
        assertEquals("2025年1月29日", prev.toString())
        assertEquals("2026年2月17日", next.toString())
        assertEquals(384, next.subtract(prev))
        assertEquals(365, next.subtract(today))
    }
}

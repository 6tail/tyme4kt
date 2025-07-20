package com.tyme.test

import com.tyme.lunar.LunarHour
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * 时辰测试
 *
 * @author 6tail
 */
class LunarHourTest {
    @Test
    fun test1() {
        val h = LunarHour(2020, -4, 5, 23, 0, 0)
        assertEquals("子时", h.getName())
        assertEquals("农历庚子年闰四月初五戊子时", h.toString())
    }

    @Test
    fun test2() {
        val h = LunarHour(2020, -4, 5, 0, 59, 0)
        assertEquals("子时", h.getName())
        assertEquals("农历庚子年闰四月初五丙子时", h.toString())
    }

    @Test
    fun test3() {
        val h = LunarHour(2020, -4, 5, 1, 0, 0)
        assertEquals("丑时", h.getName())
        assertEquals("农历庚子年闰四月初五丁丑时", h.toString())
    }

    @Test
    fun test4() {
        val h = LunarHour(2020, -4, 5, 21, 30, 0)
        assertEquals("亥时", h.getName())
        assertEquals("农历庚子年闰四月初五丁亥时", h.toString())
    }

    @Test
    fun test5() {
        val h = LunarHour(2020, -4, 2, 23, 30, 0)
        assertEquals("子时", h.getName())
        assertEquals("农历庚子年闰四月初二壬子时", h.toString())
    }

    @Test
    fun test6() {
        val h = LunarHour(2020, 4, 28, 23, 30, 0)
        assertEquals("子时", h.getName())
        assertEquals("农历庚子年四月廿八甲子时", h.toString())
    }

    @Test
    fun test7() {
        val h = LunarHour(2020, 4, 29, 0, 0, 0)
        assertEquals("子时", h.getName())
        assertEquals("农历庚子年四月廿九甲子时", h.toString())
    }

    @Test
    fun test8() {
        val h = LunarHour(2023, 11, 14, 23, 0, 0)
        val sixtyCycleHour = h.getSixtyCycleHour()
        assertEquals("甲子", h.getSixtyCycle().getName())
        assertEquals("己未", sixtyCycleHour.getDay().getName())
        assertEquals("戊午", h.getLunarDay().getSixtyCycle().getName())
        assertEquals("农历癸卯年十一月十四", h.getLunarDay().toString())

        assertEquals("甲子", sixtyCycleHour.getMonth().getName())
        assertEquals("农历癸卯年十一月", h.getLunarDay().getLunarMonth().toString())
        assertEquals("乙丑", h.getLunarDay().getLunarMonth().getSixtyCycle().getName())

        assertEquals("癸卯", sixtyCycleHour.getYear().getName())
        assertEquals("农历癸卯年", h.getLunarDay().getLunarMonth().getLunarYear().toString())
        assertEquals("癸卯", h.getLunarDay().getLunarMonth().getLunarYear().getSixtyCycle().getName())
    }

    @Test
    fun test9() {
        val h = LunarHour(2023, 11, 14, 6, 0, 0)
        val sixtyCycleHour = h.getSixtyCycleHour()
        assertEquals("乙卯", h.getSixtyCycle().getName())

        assertEquals("戊午", sixtyCycleHour.getDay().getName())
        assertEquals("戊午", h.getLunarDay().getSixtyCycle().getName())
        assertEquals("农历癸卯年十一月十四", h.getLunarDay().toString())

        assertEquals("甲子", sixtyCycleHour.getMonth().getName())
        assertEquals("农历癸卯年十一月", h.getLunarDay().getLunarMonth().toString())
        assertEquals("乙丑", h.getLunarDay().getLunarMonth().getSixtyCycle().getName())

        assertEquals("癸卯", sixtyCycleHour.getYear().getName())
        assertEquals("农历癸卯年", h.getLunarDay().getLunarMonth().getLunarYear().toString())
        assertEquals("癸卯", h.getLunarDay().getLunarMonth().getLunarYear().getSixtyCycle().getName())
    }

    @Test
    fun test28() {
        val h = LunarHour(2024, 9, 7, 10, 0, 0)
        assertEquals("留连", h.getMinorRen().getName())
    }
}

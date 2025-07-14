package com.tyme.test

import com.tyme.solar.SolarYear
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

/**
 * 公历年测试
 *
 * @author 6tail
 */
class SolarYearTest {
    @Test
    fun test0() {
        assertEquals("2023年", SolarYear(2023).getName())
    }

    @Test
    fun test1() {
        assertFalse(SolarYear(2023).isLeap())
    }

    @Test
    fun test2() {
        assertTrue(SolarYear(1500).isLeap())
    }

    @Test
    fun test3() {
        assertFalse(SolarYear(1700).isLeap())
    }

    @Test
    fun test4() {
        assertEquals(365, SolarYear(2023).getDayCount())
    }

    @Test
    fun test5() {
        assertEquals("2028年", SolarYear(2023).next(5).getName())
    }

    @Test
    fun test6() {
        assertEquals("2018年", SolarYear(2023).next(-5).getName())
    }

    /**
     * 生成公历年历示例
     */
    @Test
    fun test7() {
        val year = SolarYear(2024)
        for (month in year.getMonths()) {
            println(month)
            for (week in month.getWeeks(1)) {
                print(week.getName())
                for (day in week.getDays()) {
                    print(" " + day.getDay())
                }
                println()
            }
            println()
        }
    }
}

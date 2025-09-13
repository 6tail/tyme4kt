package com.tyme.test

import com.tyme.lunar.LunarYear
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * 农历年测试
 *
 * @author 6tail
 */
class LunarYearTest {
    @Test
    fun test0() {
        assertEquals("农历癸卯年", LunarYear(2023).getName())
    }

    @Test
    fun test1() {
        assertEquals("农历戊申年", LunarYear(2023).next(5).getName())
    }

    @Test
    fun test2() {
        assertEquals("农历戊戌年", LunarYear(2023).next(-5).getName())
    }

    /**
     * 农历年的干支
     */
    @Test
    fun test3() {
        assertEquals("庚子", LunarYear(2020).getSixtyCycle().getName())
    }

    /** 农历年的生肖(农历年.干支.地支.生肖) */
    @Test
    fun test4() {
        assertEquals(
            "虎",
            LunarYear(1986).getSixtyCycle().getEarthBranch().getZodiac().getName()
        )
    }

    @Test
    fun test5() {
        assertEquals(12, LunarYear(151).getLeapMonth())
    }

    @Test
    fun test6() {
        assertEquals(1, LunarYear(2357).getLeapMonth())
    }

    @Test
    fun test7() {
        val y = LunarYear(2023)
        assertEquals("癸卯", y.getSixtyCycle().getName())
        assertEquals("兔", y.getSixtyCycle().getEarthBranch().getZodiac().getName())
    }

    @Test
    fun test8() {
        assertEquals("上元", LunarYear(1864).getTwenty().getSixty().getName())
    }

    @Test
    fun test9() {
        assertEquals("上元", LunarYear(1923).getTwenty().getSixty().getName())
    }

    @Test
    fun test10() {
        assertEquals("中元", LunarYear(1924).getTwenty().getSixty().getName())
    }

    @Test
    fun test11() {
        assertEquals("中元", LunarYear(1983).getTwenty().getSixty().getName())
    }

    @Test
    fun test12() {
        assertEquals("下元", LunarYear(1984).getTwenty().getSixty().getName())
    }

    @Test
    fun test13() {
        assertEquals("下元", LunarYear(2043).getTwenty().getSixty().getName())
    }

    @Test
    fun test14() {
        assertEquals("一运", LunarYear(1864).getTwenty().getName())
    }

    @Test
    fun test15() {
        assertEquals("一运", LunarYear(1883).getTwenty().getName())
    }

    @Test
    fun test16() {
        assertEquals("二运", LunarYear(1884).getTwenty().getName())
    }

    @Test
    fun test17() {
        assertEquals("二运", LunarYear(1903).getTwenty().getName())
    }

    @Test
    fun test18() {
        assertEquals("三运", LunarYear(1904).getTwenty().getName())
    }

    @Test
    fun test19() {
        assertEquals("三运", LunarYear(1923).getTwenty().getName())
    }

    @Test
    fun test20() {
        assertEquals("八运", LunarYear(2004).getTwenty().getName())
    }

    @Test
    fun test21() {
        val year = LunarYear(1)
        assertEquals("六运", year.getTwenty().getName())
        assertEquals("中元", year.getTwenty().getSixty().getName())
    }

    @Test
    fun test22() {
        val year = LunarYear(1863)
        assertEquals("九运", year.getTwenty().getName())
        assertEquals("下元", year.getTwenty().getSixty().getName())
    }

    /** 生成农历年历示例 */
    @Test
    fun test23() {
        val year = LunarYear(2023)
        for (month in year.getMonths()) {
            println(month)
            for (week in month.getWeeks(1)) {
                print(week.getName())
                for (day in week.getDays()) {
                    print(" " + day.getName())
                }
                println()
            }
            println()
        }
    }
}

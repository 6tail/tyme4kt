package com.tyme.test

import com.tyme.festival.LunarFestival
import com.tyme.lunar.LunarDay
import com.tyme.solar.SolarDay
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

/**
 * 农历传统节日测试
 *
 * @author 6tail
 */
class LunarFestivalTest {
    @Test
    fun test0() {
        var i = 0
        val j = LunarFestival.NAMES.size
        while (i < j) {
            val f = LunarFestival.fromIndex(2023, i)
            assertNotNull(f)
            assertEquals(LunarFestival.NAMES[i], f!!.getName())
            i++
        }
    }

    @Test
    fun test1() {
        val f = LunarFestival.fromIndex(2023, 0)
        assertNotNull(f)
        var i = 0
        val j = LunarFestival.NAMES.size
        while (i < j) {
            assertEquals(LunarFestival.NAMES[i], f!!.next(i)!!.getName())
            i++
        }
    }

    @Test
    fun test2() {
        val f = LunarFestival.fromIndex(2023, 0)
        assertNotNull(f)
        assertEquals("农历癸卯年正月初一 春节", f.toString())
        assertEquals("农历癸卯年十一月初十 冬至节", f!!.next(10).toString())
        assertEquals("农历甲辰年正月初一 春节", f.next(13).toString())
        assertEquals("农历壬寅年十一月廿九 冬至节", f.next(-3).toString())
    }

    @Test
    fun test3() {
        val f = LunarFestival.fromIndex(2023, 0)
        assertNotNull(f)
        assertEquals("农历壬寅年三月初五 清明节", f!!.next(-9).toString())
    }

    @Test
    fun test4() {
        val f: LunarFestival? = LunarDay(2010, 1, 15).getFestival()
        assertNotNull(f)
        assertEquals("农历庚寅年正月十五 元宵节", f.toString())
    }

    @Test
    fun test5() {
        val f: LunarFestival? = LunarDay(2021, 12, 29).getFestival()
        assertNotNull(f)
        assertEquals("农历辛丑年十二月廿九 除夕", f.toString())
    }

    @Test
    fun test6() {
        val f: LunarFestival? = SolarDay(2025, 12, 21).getLunarDay().getFestival()
        assertNotNull(f)
        assertEquals("农历乙巳年十一月初二 冬至节", f.toString())
    }

    @Test
    fun test7() {
        val f: LunarFestival? = LunarDay(2025, 5, 5).getFestival()
        assertNotNull(f)
        assertEquals("农历乙巳年五月初五 端午节", f.toString())
    }

    @Test
    fun test8() {
        val f = SolarDay(2025, 12, 21).getLunarDay().getFestival()
        assertNotNull(f)
        assertEquals(f.toString(), "农历乙巳年十一月初二 冬至节")
    }
}

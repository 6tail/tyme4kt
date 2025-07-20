package com.tyme.test

import com.tyme.festival.SolarFestival
import com.tyme.solar.SolarDay
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

/**
 * 公历现代节日测试
 *
 * @author 6tail
 */
class SolarFestivalTest {
    @Test
    fun test0() {
        var i = 0
        val j = SolarFestival.NAMES.size
        while (i < j) {
            val f = SolarFestival.fromIndex(2023, i)
            assertNotNull(f)
            assertEquals(SolarFestival.NAMES[i], f!!.getName())
            i++
        }
    }

    @Test
    fun test1() {
        val f = SolarFestival.fromIndex(2023, 0)
        assertNotNull(f)
        var i = 0
        val j = SolarFestival.NAMES.size
        while (i < j) {
            assertEquals(SolarFestival.NAMES[i], f!!.next(i)!!.getName())
            i++
        }
    }

    @Test
    fun test2() {
        val f = SolarFestival.fromIndex(2023, 0)
        assertNotNull(f)
        assertEquals("2024年5月1日 五一劳动节", f!!.next(13).toString())
        assertEquals("2022年8月1日 八一建军节", f.next(-3).toString())
    }

    @Test
    fun test3() {
        val f = SolarFestival.fromIndex(2023, 0)
        assertNotNull(f)
        assertEquals("2022年3月8日 三八妇女节", f!!.next(-9).toString())
    }

    @Test
    fun test4() {
        val f: SolarFestival? = SolarDay(2010, 1, 1).getFestival()
        assertNotNull(f)
        assertEquals("2010年1月1日 元旦", f.toString())
    }

    @Test
    fun test5() {
        val f: SolarFestival? = SolarDay(2021, 5, 4).getFestival()
        assertNotNull(f)
        assertEquals("2021年5月4日 五四青年节", f.toString())
    }

    @Test
    fun test6() {
        val f: SolarFestival? = SolarDay(1939, 5, 4).getFestival()
        assertNull(f)
    }
}

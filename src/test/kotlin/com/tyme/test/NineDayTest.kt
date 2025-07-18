package com.tyme.test

import com.tyme.culture.nine.NineDay
import com.tyme.solar.SolarDay
import kotlin.test.*

/**
 * 数九测试
 *
 * @author 6tail
 */
class NineDayTest {
    @Test
    fun test0() {
        val d: NineDay? = SolarDay(2020, 12, 21).getNineDay()
        assertEquals("一九", d?.getName())
        assertEquals("一九", d?.getNine().toString())
        assertEquals("一九第1天", d.toString())
    }

    @Test
    fun test1() {
        val d: NineDay? = SolarDay(2020, 12, 22).getNineDay()
        assertEquals("一九", d?.getName())
        assertEquals("一九", d?.getNine().toString())
        assertEquals("一九第2天", d.toString())
    }

    @Test
    fun test2() {
        val d: NineDay? = SolarDay(2020, 1, 7).getNineDay()
        assertEquals("二九", d?.getName())
        assertEquals("二九", d?.getNine().toString())
        assertEquals("二九第8天", d.toString())
    }

    @Test
    fun test3() {
        val d: NineDay? = SolarDay(2021, 1, 6).getNineDay()
        assertEquals("二九", d?.getName())
        assertEquals("二九", d?.getNine().toString())
        assertEquals("二九第8天", d.toString())
    }

    @Test
    fun test4() {
        val d: NineDay? = SolarDay(2021, 1, 8).getNineDay()
        assertEquals("三九", d?.getName())
        assertEquals("三九", d?.getNine().toString())
        assertEquals("三九第1天", d.toString())
    }

    @Test
    fun test5() {
        val d: NineDay? = SolarDay(2021, 3, 5).getNineDay()
        assertEquals("九九", d?.getName())
        assertEquals("九九", d?.getNine().toString())
        assertEquals("九九第3天", d.toString())
    }

    @Test
    fun test6() {
        val d: NineDay? = SolarDay(2021, 7, 5).getNineDay()
        assertNull(d)
    }
}

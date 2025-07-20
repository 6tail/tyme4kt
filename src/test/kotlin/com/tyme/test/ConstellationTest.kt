package com.tyme.test

import com.tyme.solar.SolarDay
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * 星座测试
 *
 * @author 6tail
 */
class ConstellationTest {
    @Test
    fun test0() {
        assertEquals("白羊", SolarDay(2020, 3, 21).getConstellation().getName())
        assertEquals("白羊", SolarDay(2020, 4, 19).getConstellation().getName())
    }

    @Test
    fun test1() {
        assertEquals("金牛", SolarDay(2020, 4, 20).getConstellation().getName())
        assertEquals("金牛", SolarDay(2020, 5, 20).getConstellation().getName())
    }

    @Test
    fun test2() {
        assertEquals("双子", SolarDay(2020, 5, 21).getConstellation().getName())
        assertEquals("双子", SolarDay(2020, 6, 21).getConstellation().getName())
    }

    @Test
    fun test3() {
        assertEquals("巨蟹", SolarDay(2020, 6, 22).getConstellation().getName())
        assertEquals("巨蟹", SolarDay(2020, 7, 22).getConstellation().getName())
    }

    @Test
    fun test4() {
        var solar = SolarDay(2020, 7, 23)
        assertEquals("狮子", solar.getConstellation().getName())
        solar = SolarDay(2020, 8, 22)
        assertEquals("狮子", solar.getConstellation().getName())
    }

    @Test
    fun test5() {
        var solar = SolarDay(2020, 8, 23)
        assertEquals("处女", solar.getConstellation().getName())
        solar = SolarDay(2020, 9, 22)
        assertEquals("处女", solar.getConstellation().getName())
    }

    @Test
    fun test6() {
        var solar = SolarDay(2020, 9, 23)
        assertEquals("天秤", solar.getConstellation().getName())
        solar = SolarDay(2020, 10, 23)
        assertEquals("天秤", solar.getConstellation().getName())
    }

    @Test
    fun test7() {
        var solar = SolarDay(2020, 10, 24)
        assertEquals("天蝎", solar.getConstellation().getName())
        solar = SolarDay(2020, 11, 22)
        assertEquals("天蝎", solar.getConstellation().getName())
    }

    @Test
    fun test8() {
        var solar = SolarDay(2020, 11, 23)
        assertEquals("射手", solar.getConstellation().getName())
        solar = SolarDay(2020, 12, 21)
        assertEquals("射手", solar.getConstellation().getName())
    }

    @Test
    fun test9() {
        var solar = SolarDay(2020, 12, 22)
        assertEquals("摩羯", solar.getConstellation().getName())
        solar = SolarDay(2021, 1, 19)
        assertEquals("摩羯", solar.getConstellation().getName())
    }

    @Test
    fun test10() {
        var solar = SolarDay(2021, 1, 20)
        assertEquals("水瓶", solar.getConstellation().getName())
        solar = SolarDay(2021, 2, 18)
        assertEquals("水瓶", solar.getConstellation().getName())
    }

    @Test
    fun test11() {
        var solar = SolarDay(2021, 2, 19)
        assertEquals("双鱼", solar.getConstellation().getName())
        solar = SolarDay(2021, 3, 20)
        assertEquals("双鱼", solar.getConstellation().getName())
    }
}

package com.tyme.test

import com.tyme.culture.dog.DogDay
import com.tyme.solar.SolarDay
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

/**
 * 三伏测试
 *
 * @author 6tail
 */
class DogDayTest {
    @Test
    fun test0() {
        val d: DogDay? = SolarDay(2011, 7, 14).getDogDay()
        assertEquals("初伏", d?.getName())
        assertEquals("初伏", d?.getDog().toString())
        assertEquals("初伏第1天", d.toString())
    }

    @Test
    fun test1() {
        val d: DogDay? = SolarDay(2011, 7, 23).getDogDay()
        assertEquals("初伏", d?.getName())
        assertEquals("初伏", d?.getDog().toString())
        assertEquals("初伏第10天", d.toString())
    }

    @Test
    fun test2() {
        val d: DogDay? = SolarDay(2011, 7, 24).getDogDay()
        assertEquals("中伏", d?.getName())
        assertEquals("中伏", d?.getDog().toString())
        assertEquals("中伏第1天", d.toString())
    }

    @Test
    fun test3() {
        val d: DogDay? = SolarDay(2011, 8, 12).getDogDay()
        assertEquals("中伏", d?.getName())
        assertEquals("中伏", d?.getDog().toString())
        assertEquals("中伏第20天", d.toString())
    }

    @Test
    fun test4() {
        val d: DogDay? = SolarDay(2011, 8, 13).getDogDay()
        assertEquals("末伏", d?.getName())
        assertEquals("末伏", d?.getDog().toString())
        assertEquals("末伏第1天", d.toString())
    }

    @Test
    fun test5() {
        val d: DogDay? = SolarDay(2011, 8, 22).getDogDay()
        assertEquals("末伏", d?.getName())
        assertEquals("末伏", d?.getDog().toString())
        assertEquals("末伏第10天", d.toString())
    }

    @Test
    fun test6() {
        assertNull(SolarDay(2011, 7, 13).getDogDay())
    }

    @Test
    fun test7() {
        assertNull(SolarDay(2011, 8, 23).getDogDay())
    }

    @Test
    fun test8() {
        val d: DogDay? = SolarDay(2012, 7, 18).getDogDay()
        assertEquals("初伏", d?.getName())
        assertEquals("初伏", d?.getDog().toString())
        assertEquals("初伏第1天", d.toString())
    }

    @Test
    fun test9() {
        val d: DogDay? = SolarDay(2012, 8, 5).getDogDay()
        assertEquals("中伏", d?.getName())
        assertEquals("中伏", d?.getDog().toString())
        assertEquals("中伏第9天", d.toString())
    }

    @Test
    fun test10() {
        val d: DogDay? = SolarDay(2012, 8, 8).getDogDay()
        assertEquals("末伏", d?.getName())
        assertEquals("末伏", d?.getDog().toString())
        assertEquals("末伏第2天", d.toString())
    }
}

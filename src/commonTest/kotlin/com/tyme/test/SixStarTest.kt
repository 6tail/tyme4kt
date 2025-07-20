package com.tyme.test

import com.tyme.solar.SolarDay
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * 六曜测试
 *
 * @author 6tail
 */
class SixStarTest {
    @Test
    fun test0() {
        assertEquals("佛灭", SolarDay(2020, 4, 23).getLunarDay().getSixStar().getName())
    }

    @Test
    fun test1() {
        assertEquals("友引", SolarDay(2021, 1, 15).getLunarDay().getSixStar().getName())
    }

    @Test
    fun test2() {
        assertEquals("先胜", SolarDay(2017, 1, 5).getLunarDay().getSixStar().getName())
    }

    @Test
    fun test3() {
        assertEquals("友引", SolarDay(2020, 4, 10).getLunarDay().getSixStar().getName())
    }

    @Test
    fun test4() {
        assertEquals("大安", SolarDay(2020, 6, 11).getLunarDay().getSixStar().getName())
    }

    @Test
    fun test5() {
        assertEquals("先胜", SolarDay(2020, 6, 1).getLunarDay().getSixStar().getName())
    }

    @Test
    fun test6() {
        assertEquals("先负", SolarDay(2020, 12, 8).getLunarDay().getSixStar().getName())
    }

    @Test
    fun test8() {
        assertEquals("赤口", SolarDay(2020, 12, 11).getLunarDay().getSixStar().getName())
    }
}

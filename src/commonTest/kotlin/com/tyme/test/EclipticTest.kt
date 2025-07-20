package com.tyme.test

import com.tyme.culture.star.twelve.TwelveStar
import com.tyme.solar.SolarDay
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * 黄道黑道十二神测试
 *
 * @author 6tail
 */
class EclipticTest {
    @Test
    fun test0() {
        var star: TwelveStar = SolarDay(2023, 10, 30).getLunarDay().getTwelveStar()
        assertEquals("天德", star.getName())
        assertEquals("黄道", star.getEcliptic().getName())
        assertEquals("吉", star.getEcliptic().getLuck().getName())
        star = SolarDay(2023, 10, 30).getSixtyCycleDay().getTwelveStar()
        assertEquals("天德", star.getName())
        assertEquals("黄道", star.getEcliptic().getName())
        assertEquals("吉", star.getEcliptic().getLuck().getName())
    }

    @Test
    fun test1() {
        var star: TwelveStar = SolarDay(2023, 10, 19).getLunarDay().getTwelveStar()
        assertEquals("白虎", star.getName())
        assertEquals("黑道", star.getEcliptic().getName())
        assertEquals("凶", star.getEcliptic().getLuck().getName())
        star = SolarDay(2023, 10, 19).getSixtyCycleDay().getTwelveStar()
        assertEquals("白虎", star.getName())
        assertEquals("黑道", star.getEcliptic().getName())
        assertEquals("凶", star.getEcliptic().getLuck().getName())
    }

    @Test
    fun test2() {
        var star: TwelveStar = SolarDay(2023, 10, 7).getLunarDay().getTwelveStar()
        assertEquals("天牢", star.getName())
        assertEquals("黑道", star.getEcliptic().getName())
        assertEquals("凶", star.getEcliptic().getLuck().getName())
        star = SolarDay(2023, 10, 7).getSixtyCycleDay().getTwelveStar()
        assertEquals("天牢", star.getName())
        assertEquals("黑道", star.getEcliptic().getName())
        assertEquals("凶", star.getEcliptic().getLuck().getName())
    }

    @Test
    fun test3() {
        var star: TwelveStar = SolarDay(2023, 10, 8).getLunarDay().getTwelveStar()
        assertEquals("玉堂", star.getName())
        assertEquals("黄道", star.getEcliptic().getName())
        assertEquals("吉", star.getEcliptic().getLuck().getName())
        star = SolarDay(2023, 10, 8).getSixtyCycleDay().getTwelveStar()
        assertEquals("玉堂", star.getName())
        assertEquals("黄道", star.getEcliptic().getName())
        assertEquals("吉", star.getEcliptic().getLuck().getName())
    }
}

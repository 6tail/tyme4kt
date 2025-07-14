package com.tyme.test

import com.tyme.culture.Zodiac
import com.tyme.rabbyung.RabByungDay.Companion.fromElementZodiac
import com.tyme.rabbyung.RabByungElement
import com.tyme.solar.SolarDay
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

/**
 * 藏历日测试
 *
 * @author 6tail
 */
class RabByungDayTest {
    @Test
    fun test0() {
        assertEquals("第十六饶迥铁虎年十二月初一", SolarDay(1951, 1, 8).getRabByungDay().toString())
        assertEquals("1951年1月8日", fromElementZodiac(15, RabByungElement("铁"), Zodiac("虎"), 12, 1).getSolarDay().toString())
    }

    @Test
    fun test1() {
        assertEquals("第十八饶迥铁马年十二月三十", SolarDay(2051, 2, 11).getRabByungDay().toString())
        assertEquals("2051年2月11日", fromElementZodiac(17, RabByungElement("铁"), Zodiac("马"), 12, 30).getSolarDay().toString())
    }

    @Test
    fun test2() {
        assertEquals("第十七饶迥木蛇年二月廿五", SolarDay(2025, 4, 23).getRabByungDay().toString())
        assertEquals("2025年4月23日", fromElementZodiac(16, RabByungElement("木"), Zodiac("蛇"), 2, 25).getSolarDay().toString())
    }

    @Test
    fun test3() {
        assertEquals("第十六饶迥铁兔年正月初二", SolarDay(1951, 2, 8).getRabByungDay().toString())
        assertEquals("1951年2月8日", fromElementZodiac(15, RabByungElement("铁"), Zodiac("兔"), 1, 2).getSolarDay().toString())
    }

    @Test
    fun test4() {
        assertEquals("第十六饶迥铁虎年十二月闰十六", SolarDay(1951, 1, 24).getRabByungDay().toString())
        assertEquals("1951年1月24日", fromElementZodiac(15, RabByungElement("铁"), Zodiac("虎"), 12, -16).getSolarDay().toString())
    }

    @Test
    fun test5() {
        assertEquals("第十六饶迥铁牛年五月十一", SolarDay(1961, 6, 24).getRabByungDay().toString())
        assertEquals("1961年6月24日", fromElementZodiac(15, RabByungElement("铁"), Zodiac("牛"), 5, 11).getSolarDay().toString())
    }

    @Test
    fun test6() {
        assertEquals("第十六饶迥铁兔年十二月廿八", SolarDay(1952, 2, 23).getRabByungDay().toString())
        assertEquals("1952年2月23日", fromElementZodiac(15, RabByungElement("铁"), Zodiac("兔"), 12, 28).getSolarDay().toString())
    }

    @Test
    fun test7() {
        assertEquals("第十七饶迥木蛇年二月廿九", SolarDay(2025, 4, 26).getRabByungDay().toString())
    }

    @Test
    fun test8() {
        assertEquals("第十七饶迥木蛇年二月廿七", SolarDay(2025, 4, 25).getRabByungDay().toString())
    }
}
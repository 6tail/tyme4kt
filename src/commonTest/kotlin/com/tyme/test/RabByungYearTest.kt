package com.tyme.test

import com.tyme.culture.Zodiac
import com.tyme.rabbyung.RabByungElement
import com.tyme.rabbyung.RabByungYear
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * 藏历年测试
 *
 * @author 6tail
 */
class RabByungYearTest {
    @Test
    fun test0() {
        val y = RabByungYear.fromElementZodiac(0, RabByungElement("火"), Zodiac("兔"))
        assertEquals("第一饶迥火兔年", y.getName())
        assertEquals("1027年", y.getSolarYear().getName())
        assertEquals("丁卯", y.getSixtyCycle().getName())
        assertEquals(10, y.leapMonth)
    }

    @Test
    fun test1() {
        assertEquals("第一饶迥火兔年", RabByungYear(1027).getName())
    }

    @Test
    fun test2() {
        assertEquals("第十七饶迥铁虎年", RabByungYear(2010).getName())
    }

    @Test
    fun test3() {
        assertEquals(5, RabByungYear(2043).leapMonth)
        assertEquals(0, RabByungYear(2044).leapMonth)
    }

    @Test
    fun test4() {
        assertEquals("第十六饶迥铁牛年", RabByungYear(1961).getName())
    }

    @Test
    fun test5() {
        assertEquals(1961, RabByungYear.fromElementZodiac(15, RabByungElement("铁"), Zodiac("牛")).year)
    }
}
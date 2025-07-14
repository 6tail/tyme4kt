package com.tyme.test

import com.tyme.culture.Zodiac
import com.tyme.rabbyung.RabByungElement
import com.tyme.rabbyung.RabByungYear
import com.tyme.rabbyung.RabByungYear.Companion.fromElementZodiac
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

/**
 * 藏历年测试
 *
 * @author 6tail
 */
class RabByungYearTest {
    @Test
    fun test0() {
        val y = fromElementZodiac(0, RabByungElement("火"), Zodiac("兔"))
        assertEquals("第一饶迥火兔年", y.getName())
        assertEquals("1027年", y.getSolarYear().getName())
        assertEquals("丁卯", y.getSixtyCycle().getName())
        assertEquals(10, y.getLeapMonth())
    }

    @Test
    fun test1() {
        assertEquals("第一饶迥火兔年", RabByungYear.fromYear(1027).getName())
    }

    @Test
    fun test2() {
        assertEquals("第十七饶迥铁虎年", RabByungYear.fromYear(2010).getName())
    }

    @Test
    fun test3() {
        assertEquals(5, RabByungYear.fromYear(2043).getLeapMonth())
        assertEquals(0, RabByungYear.fromYear(2044).getLeapMonth())
    }

    @Test
    fun test4() {
        assertEquals("第十六饶迥铁牛年", RabByungYear.fromYear(1961).getName())
    }
}
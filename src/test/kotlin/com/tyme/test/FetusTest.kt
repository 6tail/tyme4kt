package com.tyme.test

import com.tyme.solar.SolarDay
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

/**
 * 胎神测试
 *
 * @author 6tail
 */
class FetusTest {
    /** 逐日胎神 */
    @Test
    fun test1() {
        assertEquals("碓磨厕 外东南", SolarDay(2021, 11, 13).getLunarDay().getFetusDay().getName())
    }

    @Test
    fun test2() {
        assertEquals("占门碓 外东南", SolarDay(2021, 11, 12).getLunarDay().getFetusDay().getName())
    }

    @Test
    fun test3() {
        assertEquals("厨灶厕 外西南", SolarDay(2011, 11, 12).getLunarDay().getFetusDay().getName())
    }
}

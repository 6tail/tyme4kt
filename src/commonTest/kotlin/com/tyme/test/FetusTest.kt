package com.tyme.test

import com.tyme.culture.fetus.FetusDay
import com.tyme.enums.Side
import com.tyme.solar.SolarDay
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * 胎神测试
 *
 * @author 6tail
 */
class FetusTest {
    /**
     * 逐日胎神
     */
    @Test
    fun test() {
        assertEquals("碓磨厕 外东南", SolarDay(2021, 11, 13).getLunarDay().getFetusDay().getName())
    }

    @Test
    fun test1() {
        assertEquals("占门碓 外东南", SolarDay(2021, 11, 12).getLunarDay().getFetusDay().getName())
    }

    @Test
    fun test2() {
        assertEquals("厨灶厕 外西南", SolarDay(2011, 11, 12).getLunarDay().getFetusDay().getName())
    }

    @Test
    fun test3() {
        val d: FetusDay = SolarDay(2011, 11, 12).getLunarDay().getFetusDay()
        assertEquals(Side.OUT, d.getSide())
        assertEquals("西南", d.getDirection().getName())
        assertEquals("厨灶", d.fetusHeavenStem.getName())
        assertEquals("厕", d.fetusEarthBranch.getName())
    }
}

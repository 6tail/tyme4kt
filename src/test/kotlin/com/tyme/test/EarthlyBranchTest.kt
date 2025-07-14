package com.tyme.test

import com.tyme.sixtycycle.EarthBranch
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

/**
 * 地支测试
 *
 * @author 6tail
 */
class EarthlyBranchTest {
    @Test
    fun test0() {
        assertEquals("子", EarthBranch(0).getName())
    }

    @Test
    fun test1() {
        assertEquals(0, EarthBranch("子").getIndex())
    }

    @Test
    fun test2() {
        // 冲
        assertEquals("午", EarthBranch("子").getOpposite().getName())
        assertEquals("辰", EarthBranch("戌").getOpposite().getName())
    }

    @Test
    fun test3() {
        // 六合
        assertEquals("丑", EarthBranch("子").getCombine().getName())
        assertEquals("巳", EarthBranch("申").getCombine().getName())
    }

    @Test
    fun test4() {
        // 六害
        assertEquals("寅", EarthBranch("巳").getHarm().getName())
        assertEquals("亥", EarthBranch("申").getHarm().getName())
    }

    @Test
    fun test5() {
        // 合化
        assertEquals("火", EarthBranch("卯").combine(EarthBranch("戌"))?.getName())
        assertEquals("火", EarthBranch("戌").combine(EarthBranch("卯"))?.getName())
        // 卯子无法合化
        assertNull(EarthBranch("卯").combine(EarthBranch("子")))
    }
}

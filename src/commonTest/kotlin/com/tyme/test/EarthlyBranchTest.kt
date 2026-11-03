package com.tyme.test

import com.tyme.enums.HideHeavenStemType
import com.tyme.enums.YinYang
import com.tyme.sixtycycle.EarthBranch
import com.tyme.sixtycycle.HeavenStem
import com.tyme.sixtycycle.HideHeavenStem
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertNull

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
        assertEquals(0, EarthBranch("子").index)
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

    @Test
    fun test6() {
        assertEquals(YinYang.YANG, EarthBranch(0).getYinYang())
    }

    @Test
    fun test7() {
        assertContentEquals(
            EarthBranch("子").getHideHeavenStems(),
            listOf(HideHeavenStem(HeavenStem("癸"), HideHeavenStemType.MAIN))
        )
    }

    @Test
    fun test8() {
        assertEquals("北", EarthBranch("子").getDirection().getName())
    }

    @Test
    fun test9() {
        assertEquals("南", EarthBranch("子").getOminous().getName())
    }

    @Test
    fun test10() {
        assertEquals("子不问卜自惹祸殃", EarthBranch("子").getPengZuEarthBranch().getName())
    }
}

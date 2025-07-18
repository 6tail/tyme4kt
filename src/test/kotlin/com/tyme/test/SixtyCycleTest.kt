package com.tyme.test

import com.tyme.sixtycycle.EarthBranch
import com.tyme.sixtycycle.HeavenStem
import com.tyme.sixtycycle.SixtyCycle
import kotlin.test.*

/**
 * 六十甲子测试
 *
 * @author 6tail
 */
class SixtyCycleTest {
    @Test
    fun test0() {
        assertEquals("丁丑", SixtyCycle(13).getName())
    }

    @Test
    fun test1() {
        assertEquals(13, SixtyCycle("丁丑").getIndex())
    }

    /**
     * 五行
     */
    @Test
    fun test2() {
        assertEquals("石榴木", SixtyCycle("辛酉").getSound().getName())
        assertEquals("剑锋金", SixtyCycle("癸酉").getSound().getName())
        assertEquals("平地木", SixtyCycle("己亥").getSound().getName())
    }

    /**
     * 旬
     */
    @Test
    fun test3() {
        assertEquals("甲子", SixtyCycle("甲子").getTen().getName())
        assertEquals("甲寅", SixtyCycle("乙卯").getTen().getName())
        assertEquals("甲申", SixtyCycle("癸巳").getTen().getName())
    }

    /**
     * 旬空
     */
    @Test
    fun test4() {
        assertContentEquals(arrayOf(EarthBranch("戌"), EarthBranch("亥")), SixtyCycle("甲子").getExtraEarthBranches())
        assertContentEquals(arrayOf(EarthBranch("子"), EarthBranch("丑")), SixtyCycle("乙卯").getExtraEarthBranches())
        assertContentEquals(arrayOf(EarthBranch("午"), EarthBranch("未")), SixtyCycle("癸巳").getExtraEarthBranches())
    }

    /**
     * 地势(长生十二神)
     */
    @Test
    fun test5() {
        assertEquals("长生", HeavenStem("丙").getTerrain(EarthBranch("寅")).getName())
        assertEquals("沐浴", HeavenStem("辛").getTerrain(EarthBranch("亥")).getName())
    }
}

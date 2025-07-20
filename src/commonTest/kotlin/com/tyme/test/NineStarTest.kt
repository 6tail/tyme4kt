package com.tyme.test

import com.tyme.culture.star.nine.NineStar
import com.tyme.lunar.LunarDay
import com.tyme.lunar.LunarHour
import com.tyme.lunar.LunarMonth
import com.tyme.lunar.LunarYear
import com.tyme.solar.SolarDay
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * 九星测试
 *
 * @author 6tail
 */
class NineStarTest {
    @Test
    fun test0() {
        val nineStar: NineStar = LunarYear(1985).getNineStar()
        assertEquals("六", nineStar.getName())
        assertEquals("六白金", nineStar.toString())
    }

    @Test
    fun test1() {
        val nineStar: NineStar = LunarYear(2022).getNineStar()
        assertEquals("五黄土", nineStar.toString())
        assertEquals("玉衡", nineStar.getDipper().toString())
    }

    @Test
    fun test2() {
        val nineStar: NineStar = LunarYear(2033).getNineStar()
        assertEquals("三碧木", nineStar.toString())
        assertEquals("天玑", nineStar.getDipper().toString())
    }

    @Test
    fun test3() {
        val nineStar: NineStar = LunarMonth.fromYm(1985, 2).getNineStar()
        assertEquals("四绿木", nineStar.toString())
        assertEquals("天权", nineStar.getDipper().toString())
    }

    @Test
    fun test4() {
        val nineStar: NineStar = LunarMonth.fromYm(1985, 2).getNineStar()
        assertEquals("四绿木", nineStar.toString())
        assertEquals("天权", nineStar.getDipper().toString())
    }

    @Test
    fun test5() {
        val nineStar: NineStar = LunarMonth.fromYm(2022, 1).getNineStar()
        assertEquals("二黑土", nineStar.toString())
        assertEquals("天璇", nineStar.getDipper().toString())
    }

    @Test
    fun test6() {
        val nineStar: NineStar = LunarMonth.fromYm(2033, 1).getNineStar()
        assertEquals("五黄土", nineStar.toString())
        assertEquals("玉衡", nineStar.getDipper().toString())
    }

    @Test
    fun test7() {
        val nineStar: NineStar = SolarDay(1985, 2, 19).getLunarDay().getNineStar()
        assertEquals("五黄土", nineStar.toString())
        assertEquals("玉衡", nineStar.getDipper().toString())
    }

    @Test
    fun test8() {
        val nineStar: NineStar = LunarDay(2022, 1, 1).getNineStar()
        assertEquals("四绿木", nineStar.toString())
        assertEquals("天权", nineStar.getDipper().toString())
    }

    @Test
    fun test9() {
        val nineStar: NineStar = LunarDay(2033, 1, 1).getNineStar()
        assertEquals("一白水", nineStar.toString())
        assertEquals("天枢", nineStar.getDipper().toString())
    }

    @Test
    fun test10() {
        val nineStar: NineStar = LunarHour(2033, 1, 1, 12, 0, 0).getNineStar()
        assertEquals("七赤金", nineStar.toString())
        assertEquals("摇光", nineStar.getDipper().toString())
    }

    @Test
    fun test11() {
        val nineStar: NineStar = LunarHour(2011, 5, 3, 23, 0, 0).getNineStar()
        assertEquals("七赤金", nineStar.toString())
        assertEquals("摇光", nineStar.getDipper().toString())
    }

    @Test
    fun test12() {
        var m: LunarMonth = LunarMonth.fromYm(2024, 11)
        assertEquals("四绿木", m.getNineStar().toString())
        m = LunarMonth.fromYm(2024, 12)
        assertEquals("三碧木", m.getNineStar().toString())
    }
}

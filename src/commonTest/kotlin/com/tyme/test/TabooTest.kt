package com.tyme.test

import com.tyme.solar.SolarDay
import com.tyme.solar.SolarTime
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * 宜忌测试
 *
 * @author 6tail
 */
class TabooTest {
    @Test
    fun test0() {
        val taboos: MutableList<String> = ArrayList()
        for (t in SolarDay(2024, 6, 26).getSixtyCycleDay().getRecommends()) {
            taboos.add(t.getName())
        }

        assertEquals(mutableListOf("嫁娶", "祭祀", "理发", "作灶", "修饰垣墙", "平治道涂", "整手足甲", "沐浴", "冠笄"), taboos)
    }

    @Test
    fun test1() {
        val taboos: MutableList<String> = ArrayList()
        for (t in SolarDay(2024, 6, 26).getSixtyCycleDay().getAvoids()) {
            taboos.add(t.getName())
        }

        assertEquals(mutableListOf("破土", "出行", "栽种"), taboos)
    }

    @Test
    fun test2() {
        val taboos: MutableList<String> = ArrayList()
        for (t in SolarTime(2024, 6, 25, 4, 0, 0).getSixtyCycleHour().getRecommends()) {
            taboos.add(t.getName())
        }

        assertEquals(emptyList<Any>(), taboos)
    }

    @Test
    fun test3() {
        val taboos: MutableList<String> = ArrayList()
        for (t in SolarTime(2024, 6, 25, 4, 0, 0).getSixtyCycleHour().getAvoids()) {
            taboos.add(t.getName())
        }

        assertEquals(listOf("诸事不宜"), taboos)
    }

    @Test
    fun test4() {
        val taboos: MutableList<String> = ArrayList()
        for (t in SolarTime(2024, 4, 22, 0, 0, 0).getSixtyCycleHour().getRecommends()) {
            taboos.add(t.getName())
        }

        assertEquals(mutableListOf("嫁娶", "交易", "开市", "安床", "祭祀", "求财"), taboos)
    }

    @Test
    fun test5() {
        val taboos: MutableList<String> = ArrayList()
        for (t in SolarTime(2024, 4, 22, 0, 0, 0).getSixtyCycleHour().getAvoids()) {
            taboos.add(t.getName())
        }

        assertEquals(mutableListOf("出行", "移徙", "赴任", "词讼", "祈福", "修造", "求嗣"), taboos)
    }

    @Test
    fun test6() {
        val taboos: MutableList<String> = ArrayList()
        for (t in SolarDay(2021, 3, 7).getSixtyCycleDay().getRecommends()) {
            taboos.add(t.getName())
        }

        assertEquals(mutableListOf("裁衣", "经络", "伐木", "开柱眼", "拆卸", "修造", "动土", "上梁", "合脊", "合寿木", "入殓", "除服", "成服", "移柩", "破土", "安葬", "启钻", "修坟", "立碑"), taboos)
    }

    @Test
    fun test7() {
        val taboos: MutableList<String> = ArrayList()
        for (t in SolarDay(2024, 6, 26).getLunarDay().getRecommends()) {
            taboos.add(t.getName())
        }

        assertEquals(mutableListOf("嫁娶", "祭祀", "理发", "作灶", "修饰垣墙", "平治道涂", "整手足甲", "沐浴", "冠笄"), taboos)
    }

    @Test
    fun test8() {
        val taboos: MutableList<String> = ArrayList()
        for (t in SolarDay(2024, 6, 26).getLunarDay().getAvoids()) {
            taboos.add(t.getName())
        }

        assertEquals(mutableListOf("破土", "出行", "栽种"), taboos)
    }

    @Test
    fun test9() {
        val taboos: MutableList<String> = ArrayList()
        for (t in SolarTime(2024, 6, 25, 4, 0, 0).getLunarHour().getRecommends()) {
            taboos.add(t.getName())
        }

        assertEquals(emptyList<String>(), taboos)
    }

    @Test
    fun test10() {
        val taboos: MutableList<String> = ArrayList()
        for (t in SolarTime(2024, 6, 25, 4, 0, 0).getLunarHour().getAvoids()) {
            taboos.add(t.getName())
        }

        assertEquals(listOf("诸事不宜"), taboos)
    }

    @Test
    fun test11() {
        val taboos: MutableList<String> = ArrayList()
        for (t in SolarTime(2024, 4, 22, 0, 0, 0).getLunarHour().getRecommends()) {
            taboos.add(t.getName())
        }

        assertEquals(mutableListOf("嫁娶", "交易", "开市", "安床", "祭祀", "求财"), taboos)
    }

    @Test
    fun test12() {
        val taboos: MutableList<String> = ArrayList()
        for (t in SolarTime(2024, 4, 22, 0, 0, 0).getLunarHour().getAvoids()) {
            taboos.add(t.getName())
        }

        assertEquals(mutableListOf("出行", "移徙", "赴任", "词讼", "祈福", "修造", "求嗣"), taboos)
    }

    @Test
    fun test13() {
        val taboos: MutableList<String> = ArrayList()
        for (t in SolarDay(2021, 3, 7).getLunarDay().getRecommends()) {
            taboos.add(t.getName())
        }

        assertEquals(mutableListOf("裁衣", "经络", "伐木", "开柱眼", "拆卸", "修造", "动土", "上梁", "合脊", "合寿木", "入殓", "除服", "成服", "移柩", "破土", "安葬", "启钻", "修坟", "立碑"), taboos)
    }
}

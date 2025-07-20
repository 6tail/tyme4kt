package com.tyme.test

import com.tyme.culture.God
import com.tyme.solar.SolarDay
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * 神煞测试
 *
 * @author 6tail
 */
class GodTest {
    @Test
    fun test0() {
        val gods: List<God> = SolarDay(2004, 2, 16).getSixtyCycleDay().getGods()
        val ji: MutableList<String> = ArrayList()
        for (god in gods) {
            if ("吉" == god.getLuck().getName()) {
                ji.add(god.getName())
            }
        }

        val xiong: MutableList<String> = ArrayList()
        for (god in gods) {
            if ("凶" == god.getLuck().getName()) {
                xiong.add(god.getName())
            }
        }
        assertEquals(mutableListOf("天恩", "续世", "明堂"), ji)
        assertEquals(mutableListOf("月煞", "月虚", "血支", "天贼", "五虚", "土符", "归忌", "血忌"), xiong)
    }

    @Test
    fun test1() {
        val gods: List<God> = SolarDay(2029, 11, 16).getSixtyCycleDay().getGods()
        val ji: MutableList<String> = ArrayList()
        for (god in gods) {
            if ("吉" == god.getLuck().getName()) {
                ji.add(god.getName())
            }
        }

        val xiong: MutableList<String> = ArrayList()
        for (god in gods) {
            if ("凶" == god.getLuck().getName()) {
                xiong.add(god.getName())
            }
        }
        assertEquals(mutableListOf("天德合", "月空", "天恩", "益后", "金匮"), ji)
        assertEquals(mutableListOf("月煞", "月虚", "血支", "五虚"), xiong)
    }

    @Test
    fun test2() {
        val gods: List<God> = SolarDay(1954, 7, 16).getSixtyCycleDay().getGods()

        // 吉神宜趋
        val ji: MutableList<String> = ArrayList()
        for (god in gods) {
            if ("吉" == god.getLuck().getName()) {
                ji.add(god.getName())
            }
        }

        // 凶神宜忌
        val xiong: MutableList<String> = ArrayList()
        for (god in gods) {
            if ("凶" == god.getLuck().getName()) {
                xiong.add(god.getName())
            }
        }

        assertEquals(mutableListOf("民日", "天巫", "福德", "天仓", "不将", "续世", "除神", "鸣吠"), ji)
        assertEquals(mutableListOf("劫煞", "天贼", "五虚", "五离"), xiong)
    }

    @Test
    fun test3() {
        val gods: List<God> = SolarDay(2024, 12, 27).getSixtyCycleDay().getGods()
        val ji: MutableList<String> = ArrayList()
        for (god in gods) {
            if ("吉" == god.getLuck().getName()) {
                ji.add(god.getName())
            }
        }

        val xiong: MutableList<String> = ArrayList()
        for (god in gods) {
            if ("凶" == god.getLuck().getName()) {
                xiong.add(god.getName())
            }
        }
        assertEquals(mutableListOf("天恩", "四相", "阴德", "守日", "吉期", "六合", "普护", "宝光"), ji)
        assertEquals(mutableListOf("三丧", "鬼哭"), xiong)
    }

    @Test
    fun test4() {
        val gods: List<God> = SolarDay(2024, 9, 27).getSixtyCycleDay().getGods()
        val ji: MutableList<String> = ArrayList()
        for (god in gods) {
            if ("吉" == god.getLuck().getName()) {
                ji.add(god.getName())
            }
        }

        val xiong: MutableList<String> = ArrayList()
        for (god in gods) {
            if ("凶" == god.getLuck().getName()) {
                xiong.add(god.getName())
            }
        }
        assertEquals(mutableListOf("月空", "不将", "福生", "金匮", "鸣吠"), ji)
        assertEquals(mutableListOf("天罡", "大时", "大败", "咸池", "天贼", "九坎", "九焦"), xiong)
    }

    @Test
    fun test5() {
        val gods: List<God> = SolarDay(2004, 2, 16).getLunarDay().getGods()
        val ji: MutableList<String> = ArrayList()
        for (god in gods) {
            if ("吉" == god.getLuck().getName()) {
                ji.add(god.getName())
            }
        }

        val xiong: MutableList<String> = ArrayList()
        for (god in gods) {
            if ("凶" == god.getLuck().getName()) {
                xiong.add(god.getName())
            }
        }
        assertEquals(mutableListOf("天恩", "续世", "明堂"), ji)
        assertEquals(mutableListOf("月煞", "月虚", "血支", "天贼", "五虚", "土符", "归忌", "血忌"), xiong)
    }

    @Test
    fun test6() {
        val gods: List<God> = SolarDay(2029, 11, 16).getLunarDay().getGods()
        val ji: MutableList<String> = ArrayList()
        for (god in gods) {
            if ("吉" == god.getLuck().getName()) {
                ji.add(god.getName())
            }
        }

        val xiong: MutableList<String> = ArrayList()
        for (god in gods) {
            if ("凶" == god.getLuck().getName()) {
                xiong.add(god.getName())
            }
        }
        assertEquals(mutableListOf("天德合", "月空", "天恩", "益后", "金匮"), ji)
        assertEquals(mutableListOf("月煞", "月虚", "血支", "五虚"), xiong)
    }

    @Test
    fun test7() {
        val gods: List<God> = SolarDay(1954, 7, 16).getLunarDay().getGods()

        // 吉神宜趋
        val ji: MutableList<String> = ArrayList()
        for (god in gods) {
            if ("吉" == god.getLuck().getName()) {
                ji.add(god.getName())
            }
        }

        // 凶神宜忌
        val xiong: MutableList<String> = ArrayList()
        for (god in gods) {
            if ("凶" == god.getLuck().getName()) {
                xiong.add(god.getName())
            }
        }

        assertEquals(mutableListOf("民日", "天巫", "福德", "天仓", "不将", "续世", "除神", "鸣吠"), ji)
        assertEquals(mutableListOf("劫煞", "天贼", "五虚", "五离"), xiong)
    }

    @Test
    fun test8() {
        val gods: List<God> = SolarDay(2024, 12, 27).getLunarDay().getGods()
        val ji: MutableList<String> = ArrayList()
        for (god in gods) {
            if ("吉" == god.getLuck().getName()) {
                ji.add(god.getName())
            }
        }

        val xiong: MutableList<String> = ArrayList()
        for (god in gods) {
            if ("凶" == god.getLuck().getName()) {
                xiong.add(god.getName())
            }
        }
        assertEquals(mutableListOf("天恩", "四相", "阴德", "守日", "吉期", "六合", "普护", "宝光"), ji)
        assertEquals(mutableListOf("三丧", "鬼哭"), xiong)
    }

    @Test
    fun test9() {
        val gods: List<God> = SolarDay(2024, 9, 27).getLunarDay().getGods()
        val ji: MutableList<String> = ArrayList()
        for (god in gods) {
            if ("吉" == god.getLuck().getName()) {
                ji.add(god.getName())
            }
        }

        val xiong: MutableList<String> = ArrayList()
        for (god in gods) {
            if ("凶" == god.getLuck().getName()) {
                xiong.add(god.getName())
            }
        }
        assertEquals(mutableListOf("月空", "不将", "福生", "金匮", "鸣吠"), ji)
        assertEquals(mutableListOf("天罡", "大时", "大败", "咸池", "天贼", "九坎", "九焦"), xiong)
    }
}

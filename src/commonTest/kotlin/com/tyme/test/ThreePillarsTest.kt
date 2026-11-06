package com.tyme.test

import com.tyme.sixtycycle.ThreePillars
import com.tyme.solar.SolarDay
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * 三柱测试
 *
 * @author 6tail
 */
class ThreePillarsTest {
    @Test
    fun test50() {
        val solarDays: List<SolarDay> = ThreePillars("甲戌", "甲戌", "甲戌").getSolarDays(1, 2200)
        val actual: MutableList<String> = ArrayList()
        for (solarDay in solarDays) {
            actual.add(solarDay.toString())
        }

        val expected: MutableList<String> = ArrayList()
        expected.add("14年10月17日")
        expected.add("194年11月1日")
        expected.add("254年10月17日")
        expected.add("434年11月1日")
        expected.add("494年10月17日")
        expected.add("674年11月1日")
        expected.add("734年10月17日")
        expected.add("794年10月2日")
        expected.add("974年10月17日")
        expected.add("1034年10月2日")
        expected.add("1214年10月17日")
        expected.add("1274年10月2日")
        expected.add("1454年10月17日")
        expected.add("1514年10月2日")
        expected.add("1694年10月27日")
        expected.add("1754年10月13日")
        expected.add("1934年10月30日")
        expected.add("1994年10月15日")
        expected.add("2174年10月31日")
        assertEquals(expected, actual)
    }

    @Test
    fun test1(){
        assertEquals("甲戌 甲戌 甲戌", SolarDay(1034, 10, 2).getSixtyCycleDay().getThreePillars().getName())
    }
}
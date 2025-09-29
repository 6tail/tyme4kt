package com.tyme.test

import com.tyme.culture.phenology.Phenology
import com.tyme.solar.SolarDay
import com.tyme.solar.SolarTime
import kotlin.test.Test
import kotlin.test.assertEquals


/**
 * 物候测试
 *
 * @author 6tail
 */
class PhenologyTest {
    @Test
    fun test0() {
        val solarDay = SolarDay(2020, 4, 23)
        // 七十二候
        val phenology = solarDay.getPhenologyDay()
        // 三候
        val threePhenology = phenology.getPhenology().getThreePhenology()
        assertEquals("谷雨", solarDay.getTerm().getName())
        assertEquals("初候", threePhenology.getName())
        assertEquals("萍始生", phenology.getName())
        assertEquals("2020年4月19日", phenology.getPhenology().getJulianDay().getSolarDay().toString())
        assertEquals("2020年4月19日 22:45:29", phenology.getPhenology().getJulianDay().getSolarTime().toString())
        // 该候的第5天
        assertEquals(4, phenology.getDayIndex().toLong())
    }

    @Test
    fun test1() {
        val solarDay = SolarDay(2021, 12, 26)
        // 七十二候
        val phenology = solarDay.getPhenologyDay()
        // 三候
        val threePhenology = phenology.getPhenology().getThreePhenology()
        assertEquals("冬至", solarDay.getTerm().getName())
        assertEquals("2021年12月21日", solarDay.getTerm().getJulianDay().getSolarDay().toString())
        assertEquals("二候", threePhenology.getName())
        assertEquals("麋角解", phenology.getName())
        assertEquals("2021年12月26日", phenology.getPhenology().getJulianDay().getSolarDay().toString())
        assertEquals("2021年12月26日 21:48:55", phenology.getPhenology().getJulianDay().getSolarTime().toString())
        // 该候的第1天
        assertEquals(0, phenology.getDayIndex().toLong())
    }

    @Test
    fun test2() {
        val p = Phenology(2026, 1)
        val jd = p.getJulianDay()
        assertEquals("麋角解", p.getName())
        assertEquals("2025年12月26日", jd.getSolarDay().toString())
        assertEquals("2025年12月26日 20:49:56", jd.getSolarTime().toString())
    }

    @Test
    fun test3() {
        val p = SolarDay(2025, 12, 26).getPhenology()
        val jd = p.getJulianDay()
        assertEquals("麋角解", p.getName())
        assertEquals("2025年12月26日", jd.getSolarDay().toString())
        assertEquals("2025年12月26日 20:49:56", jd.getSolarTime().toString())
    }

    @Test
    fun test4() {
        assertEquals("蚯蚓结", SolarTime(2025, 12, 26, 20, 49, 38).getPhenology().getName())
        assertEquals("麋角解", SolarTime(2025, 12, 26, 20, 49, 56).getPhenology().getName())
    }
}

package com.tyme.test

import com.tyme.lunar.LunarDay
import kotlin.test.*
/**
 * 农历日测试
 *
 * @author 6tail
 */
class LunarDayTest {
    @Test
    fun test1() {
        assertEquals("1年1月1日", LunarDay(0, 11, 18).getSolarDay().toString())
    }

    @Test
    fun test2() {
        assertEquals("9999年12月31日", LunarDay(9999, 12, 2).getSolarDay().toString())
    }

    @Test
    fun test3() {
        assertEquals("1905年2月4日", LunarDay(1905, 1, 1).getSolarDay().toString())
    }

    @Test
    fun test4() {
        assertEquals("2039年1月23日", LunarDay(2038, 12, 29).getSolarDay().toString())
    }

    @Test
    fun test5() {
        assertEquals("1500年1月31日", LunarDay(1500, 1, 1).getSolarDay().toString())
    }

    @Test
    fun test6() {
        assertEquals("1501年1月18日", LunarDay(1500, 12, 29).getSolarDay().toString())
    }

    @Test
    fun test7() {
        assertEquals("1582年10月4日", LunarDay(1582, 9, 18).getSolarDay().toString())
    }

    @Test
    fun test8() {
        assertEquals("1582年10月15日", LunarDay(1582, 9, 19).getSolarDay().toString())
    }

    @Test
    fun test9() {
        assertEquals("2020年1月6日", LunarDay(2019, 12, 12).getSolarDay().toString())
    }

    @Test
    fun test10() {
        assertEquals("2033年12月22日", LunarDay(2033, -11, 1).getSolarDay().toString())
    }

    @Test
    fun test11() {
        assertEquals("2021年7月16日", LunarDay(2021, 6, 7).getSolarDay().toString())
    }

    @Test
    fun test12() {
        assertEquals("2034年2月19日", LunarDay(2034, 1, 1).getSolarDay().toString())
    }

    @Test
    fun test13() {
        assertEquals("2034年1月20日", LunarDay(2033, 12, 1).getSolarDay().toString())
    }

    @Test
    fun test14() {
        assertEquals("7013年12月24日", LunarDay(7013, -11, 4).getSolarDay().toString())
    }

    @Test
    fun test15() {
        assertEquals("己亥", LunarDay(2023, 8, 24).getSixtyCycle().toString())
    }

    @Test
    fun test16() {
        assertEquals("癸酉", LunarDay(1653, 1, 6).getSixtyCycle().toString())
    }

    @Test
    fun test17() {
        assertEquals("农历庚寅年二月初二", LunarDay(2010, 1, 1).next(31).toString())
    }

    @Test
    fun test18() {
        assertEquals("农历壬辰年闰四月初一", LunarDay(2012, 3, 1).next(60).toString())
    }

    @Test
    fun test19() {
        assertEquals("农历壬辰年闰四月廿九", LunarDay(2012, 3, 1).next(88).toString())
    }

    @Test
    fun test20() {
        assertEquals("农历壬辰年五月初一", LunarDay(2012, 3, 1).next(89).toString())
    }

    @Test
    fun test21() {
        assertEquals("2020年4月23日", LunarDay(2020, 4, 1).getSolarDay().toString())
    }

    @Test
    fun test22() {
        assertEquals("甲辰", LunarDay(2024, 1, 1).getLunarMonth().getLunarYear().getSixtyCycle().getName())
    }

    @Test
    fun test23() {
        assertEquals("癸卯", LunarDay(2023, 12, 30).getLunarMonth().getLunarYear().getSixtyCycle().getName())
    }

    /**
     * 二十八宿
     */
    @Test
    fun test24() {
        val d = LunarDay(2020, 4, 13)
        val star = d.getTwentyEightStar()
        assertEquals("南", star.getZone().getName())
        assertEquals("朱雀", star.getZone().getBeast().getName())
        assertEquals("翼", star.getName())
        assertEquals("火", star.getSevenStar().getName())
        assertEquals("蛇", star.getAnimal().getName())
        assertEquals("凶", star.getLuck().getName())

        assertEquals("阳天", star.getLand().getName())
        assertEquals("东南", star.getLand().getDirection().getName())
    }

    @Test
    fun test25() {
        val d = LunarDay(2023, 9, 28)
        val star = d.getTwentyEightStar()
        assertEquals("南", star.getZone().getName())
        assertEquals("朱雀", star.getZone().getBeast().getName())
        assertEquals("柳", star.getName())
        assertEquals("土", star.getSevenStar().getName())
        assertEquals("獐", star.getAnimal().getName())
        assertEquals("凶", star.getLuck().getName())

        assertEquals("炎天", star.getLand().getName())
        assertEquals("南", star.getLand().getDirection().getName())
    }

    @Test
    fun test26() {
        val lunar = LunarDay(2005, 11, 23)
        assertEquals("戊子", lunar.getLunarMonth().getSixtyCycle().getName())
        assertEquals("戊子", lunar.getSixtyCycleDay().getMonth().getName())
    }

    @Test
    fun test27() {
        val lunar = LunarDay(2024, 1, 1)
        assertEquals("农历甲辰年二月初三", lunar.next(31).toString())
    }

    @Test
    fun test28() {
        val lunar = LunarDay(2024, 3, 5)
        assertEquals("大安", lunar.getMinorRen().getName())
    }
}

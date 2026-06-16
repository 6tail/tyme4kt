package com.tyme.test

import com.tyme.hijri.HijriDay
import com.tyme.solar.SolarDay
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * 回历日测试
 *
 * @author 6tail
 */
class HijriDayTest {
    @Test
    fun test0() {
        assertEquals("1年穆哈兰姆月1日", SolarDay.fromYmd(622, 7, 16).getHijriDay().toString())
    }

    @Test
    fun test1() {
        assertEquals("1447年都尔喀尔德月26日", SolarDay.fromYmd(2026, 5, 13).getHijriDay().toString())
        assertEquals("2026年5月13日", HijriDay.fromYmd(1447, 11, 26).getSolarDay().toString())
    }

    @Test
    fun test2() {
        assertEquals("-538年都尔黑哲月12日", SolarDay.fromYmd(100, 7, 8).getHijriDay().toString())
        assertEquals("100年7月8日", HijriDay.fromYmd(-538, 12, 12).getSolarDay().toString())
    }

    @Test
    fun test3() {
        assertEquals("0年都尔黑哲月29日", SolarDay.fromYmd(622, 7, 15).getHijriDay().toString())
        assertEquals("622年7月15日", HijriDay.fromYmd(0, 12, 29).getSolarDay().toString())
    }

    @Test
    fun test4() {
        assertEquals("-640年主马达·敖外鲁月16日", SolarDay.fromYmd(1, 1, 1).getHijriDay().toString())
        assertEquals("1年1月1日", HijriDay.fromYmd(-640, 5, 16).getSolarDay().toString())
    }

    @Test
    fun test5() {
        assertEquals("9666年赖比尔·阿色尼月2日", SolarDay.fromYmd(9999, 12, 31).getHijriDay().toString())
        assertEquals("9999年12月31日", HijriDay.fromYmd(9666, 4, 2).getSolarDay().toString())
    }
    
}

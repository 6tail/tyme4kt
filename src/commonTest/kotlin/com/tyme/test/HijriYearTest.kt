package com.tyme.test

import com.tyme.hijri.HijriYear
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * 回历年测试
 *
 * @author 6tail
 */
class HijriYearTest {
    @Test
    fun test1() {
        assertFalse(HijriYear(1).isLeap())
        assertTrue(HijriYear(2).isLeap())
        assertFalse(HijriYear(0).isLeap())
        assertTrue(HijriYear(-1).isLeap())
    }
}
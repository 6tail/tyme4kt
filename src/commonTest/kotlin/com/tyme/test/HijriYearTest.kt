package com.tyme.test

import com.tyme.hijri.HijriYear.Companion.fromYear
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
        assertFalse(fromYear(1).isLeap())
        assertTrue(fromYear(2).isLeap())
        assertFalse(fromYear(0).isLeap())
        assertTrue(fromYear(-1).isLeap())
    }
}

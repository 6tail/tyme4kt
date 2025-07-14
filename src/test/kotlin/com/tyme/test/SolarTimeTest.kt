package com.tyme.test

import com.tyme.solar.SolarTime
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

/**
 * 公历时刻测试
 *
 * @author 6tail
 */
class SolarTimeTest {
    @Test
    fun test0() {
        val time = SolarTime(2023, 1, 1, 13, 5, 20)
        assertEquals("13:05:20", time.getName())
        assertEquals("13:04:59", time.next(-21).getName())
    }

    @Test
    fun test1() {
        val time = SolarTime(2023, 1, 1, 13, 5, 20)
        assertEquals("13:05:20", time.getName())
        assertEquals("14:06:01", time.next(3641).getName())
    }
}

package com.tyme.test

import com.tyme.sixtycycle.SixtyCycleMonth.Companion.fromIndex
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

/**
 * 干支月测试
 *
 * @author 6tail
 */
class SixtyCycleMonthTest{
    @Test
    fun test23() {
        val month = fromIndex(2025, 0)
        assertEquals("乙巳年戊寅月", month.toString())
    }
}
package com.tyme.test

import com.tyme.sixtycycle.SixtyCycleMonth
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * 干支月测试
 *
 * @author 6tail
 */
class SixtyCycleMonthTest{
    @Test
    fun test23() {
        val month = SixtyCycleMonth.fromIndex(2025, 0)
        assertEquals("乙巳年戊寅月", month.toString())
    }
}
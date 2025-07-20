package com.tyme.test

import com.tyme.rabbyung.RabByungMonth.Companion.fromYm
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * 藏历月测试
 *
 * @author 6tail
 */
class RabByungMonthTest{
    @Test
    fun test0() {
        assertEquals("第十六饶迥铁虎年十二月", fromYm(1950, 12).toString())
    }
}
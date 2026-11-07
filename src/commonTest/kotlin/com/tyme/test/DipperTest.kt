package com.tyme.test

import com.tyme.culture.star.nine.Dipper
import kotlin.test.Test
import kotlin.test.assertEquals

class DipperTest {
    @Test
    fun test0() {
        assertEquals(0, Dipper("天枢").index)
    }
}

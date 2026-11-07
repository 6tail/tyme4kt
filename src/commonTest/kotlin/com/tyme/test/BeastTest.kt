package com.tyme.test

import com.tyme.culture.Beast
import kotlin.test.Test
import kotlin.test.assertEquals

class BeastTest {
    @Test
    fun test() {
        assertEquals("东", Beast("青龙").getZone().getName())
    }
}

package com.tyme.test

import com.tyme.culture.Animal
import kotlin.test.Test
import kotlin.test.assertEquals

class AnimalTest {
    @Test
    fun test0() {
        assertEquals("亢", Animal("龙").getTwentyEightStar().getName())
    }
}

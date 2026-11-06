package com.tyme.test

import com.tyme.culture.Element
import com.tyme.sixtycycle.EarthBranch
import com.tyme.sixtycycle.HeavenStem
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * 五行测试
 *
 * @author 6tail
 */
class ElementTest {
    /**
     * 金克木
     */
    @Test
    fun test0() {
        assertEquals(Element("木"), Element("金").getRestrain())
    }

    /**
     * 火生土
     */
    @Test
    fun test1() {
        assertEquals(Element("土"), Element("火").getReinforce())
    }

    @Test
    fun test2() {
        assertEquals("火", HeavenStem("丙").getElement().getName())
    }

    @Test
    fun test3() {
        val e: Element = EarthBranch("寅").getElement()
        // 地支寅的五行为木
        assertEquals("木", e.getName())
        // 地支寅的五行(木)生火
        assertEquals(e.getReinforce().getName(), Element("火").getName())
        assertEquals("水", e.getReinforced().getName())
        assertEquals("金", e.getRestrained().getName())
    }

    /**
     * 生我的：火生土
     */
    @Test
    fun test4() {
        assertEquals(Element("火"), Element("土").getReinforced())
    }
}

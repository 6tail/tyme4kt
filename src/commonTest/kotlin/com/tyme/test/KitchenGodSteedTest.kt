package com.tyme.test

import com.tyme.culture.KitchenGodSteed
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * 灶马头测试
 *
 * @author 6tail
 */
class KitchenGodSteedTest {
    @Test
    fun test1() {
        assertEquals("二龙治水", KitchenGodSteed(2017).getDragon())
        assertEquals("二龙治水", KitchenGodSteed(2018).getDragon())
        assertEquals("八龙治水", KitchenGodSteed(2019).getDragon())
        assertEquals("三龙治水", KitchenGodSteed(5).getDragon())
    }

    @Test
    fun test2() {
        assertEquals("二人分饼", KitchenGodSteed(2017).getCake())
        assertEquals("八人分饼", KitchenGodSteed(2018).getCake())
        assertEquals("一人分饼", KitchenGodSteed(5).getCake())
    }

    @Test
    fun test3() {
        assertEquals("十一牛耕田", KitchenGodSteed(2021).getCattle())
    }

    @Test
    fun test4() {
        assertEquals("三日得金", KitchenGodSteed(2018).getGold())
    }
}
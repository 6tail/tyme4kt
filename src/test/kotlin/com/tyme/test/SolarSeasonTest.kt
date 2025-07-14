package com.tyme.test

import com.tyme.solar.SolarSeason
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

/**
 * 公历季度测试
 *
 * @author 6tail
 */
class SolarSeasonTest {
    @Test
    fun test0() {
        val season = SolarSeason(2023, 0)
        assertEquals("2023年一季度", season.toString())
        assertEquals("2021年四季度", season.next(-5).toString())
    }
}

package com.tyme.test

import com.tyme.holiday.LegalHoliday
import com.tyme.holiday.LegalHoliday.Companion.fromYmd
import com.tyme.solar.SolarDay
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

/**
 * 法定节假日测试
 *
 * @author 6tail
 */
class LegalHolidayTest {
    @Test
    fun test0() {
        val d = fromYmd(2011, 5, 1)
        assertNotNull(d)
        assertEquals("2011年5月1日 劳动节(休)", d.toString())
        assertEquals("2011年5月2日 劳动节(休)", d?.next(1).toString())
        assertEquals("2011年6月4日 端午节(休)", d?.next(2).toString())
        assertEquals("2011年4月30日 劳动节(休)", d?.next(-1).toString())
        assertEquals("2011年4月5日 清明节(休)", d?.next(-2).toString())
    }

    @Test
    fun test1() {
        val d = fromYmd(2010, 1, 1)
        assertNotNull(d)
    }

    @Test
    fun test2() {
        val d = fromYmd(2010, 1, 1)
        assertNotNull(d)
    }

    @Test
    fun test3() {
        val d = fromYmd(2001, 12, 29)
        assertNotNull(d)
        assertEquals("2001年12月29日 元旦节(班)", d.toString())
        assertNull(d?.next(-1))
    }

    @Test
    fun test4() {
        val d = fromYmd(2022, 10, 5)
        assertNotNull(d)
        assertEquals("2022年10月5日 国庆节(休)", d.toString())
        assertEquals("2022年10月4日 国庆节(休)", d!!.next(-1).toString())
        assertEquals("2022年10月6日 国庆节(休)", d.next(1).toString())
    }

    @Test
    fun test5() {
        val d: LegalHoliday? = SolarDay(2010, 10, 1).getLegalHoliday()
        assertNotNull(d)
        assertEquals("2010年10月1日 国庆节(休)", d.toString())
    }
}

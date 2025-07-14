package com.tyme.test

import com.tyme.solar.SolarDay
import com.tyme.solar.SolarTerm
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

/**
 * 节气测试
 *
 * @author 6tail
 */
class SolarTermTest {
    @Test
    fun test0() {
        // 冬至在去年，2022-12-22 05:48:01
        val dongZhi = SolarTerm(2023, "冬至")
        assertEquals("冬至", dongZhi.getName())
        assertEquals(0, dongZhi.getIndex().toLong())
        // 儒略日
        assertEquals(2459935.7416744065, dongZhi.getJulianDay().getDay(), 0.00001)
        // 公历日
        assertEquals("2022年12月22日", dongZhi.getJulianDay().getSolarDay().toString())

        // 冬至顺推23次，就是大雪 2023-12-07 17:32:44
        val daXue = dongZhi.next(23)
        assertEquals("大雪", daXue.getName())
        assertEquals(23, daXue.getIndex().toLong())
        assertEquals(2460286.2310636267, daXue.getJulianDay().getDay(), 0.00001)
        assertEquals("2023年12月7日", daXue.getJulianDay().getSolarDay().toString())

        // 冬至逆推2次，就是上一年的小雪 2022-11-22 16:20:18
        val xiaoXue = dongZhi.next(-2)
        assertEquals("小雪", xiaoXue.getName())
        assertEquals(22, xiaoXue.getIndex().toLong())
        assertEquals(2459906.180768333, xiaoXue.getJulianDay().getDay(), 0.00001)
        assertEquals("2022年11月22日", xiaoXue.getJulianDay().getSolarDay().toString())

        // 冬至顺推24次，就是下一个冬至 2023-12-22 11:27:09
        val dongZhi2 = dongZhi.next(24)
        assertEquals("冬至", dongZhi2.getName())
        assertEquals(0, dongZhi2.getIndex().toLong())
        assertEquals(2460300.9771862123, dongZhi2.getJulianDay().getDay(), 0.00001)
        assertEquals("2023年12月22日", dongZhi2.getJulianDay().getSolarDay().toString())
    }

    @Test
    fun test1() {
        // 公历2023年的雨水，2023-02-19 06:34:05
        val jq = SolarTerm(2023, "雨水")
        assertEquals("雨水", jq.getName())
        assertEquals(4, jq.getIndex().toLong())
        assertEquals(2459994.7736741747, jq.getJulianDay().getDay(), 0.00001)
    }

    @Test
    fun test2() {
        // 公历2023年的大雪，2023-12-07 17:32:44
        val jq = SolarTerm(2023, "大雪")
        assertEquals("大雪", jq.getName())
        // 索引
        assertEquals(23, jq.getIndex().toLong())
        // 公历
        assertEquals("2023年12月7日", jq.getJulianDay().getSolarDay().toString())
        // 农历
        assertEquals("农历癸卯年十月廿五", jq.getJulianDay().getSolarDay().getLunarDay().toString())
        // 儒略日
        assertEquals(2460286.2310636267, jq.getJulianDay().getDay(), 0.00001)
        // 推移
        assertEquals("雨水", jq.next(5).getName())
    }

    @Test
    fun test3() {
        assertEquals("寒露", SolarDay(2023, 10, 10).getTerm().getName())
    }

    @Test
    fun test4() {
        // 大雪当天
        assertEquals("大雪第1天", SolarDay(2023, 12, 7).getTermDay().toString())
        // 天数索引
        assertEquals(0, SolarDay(2023, 12, 7).getTermDay().getDayIndex())

        assertEquals("大雪第2天", SolarDay(2023, 12, 8).getTermDay().toString())
        assertEquals("大雪第15天", SolarDay(2023, 12, 21).getTermDay().toString())

        assertEquals("冬至第1天", SolarDay(2023, 12, 22).getTermDay().toString())
    }

    @Test
    fun test5() {
        assertEquals("2024年1月6日 04:49:08", SolarTerm(2024, "小寒").getJulianDay().getSolarTime().toString())
    }

    @Test
    fun test100(){
        for(year in 2005..2030){
            for (index in 0..24) {
                System.out.println(SolarTerm(year, index).getJulianDay().getSolarTime().toString())
            }
        }
    }
}

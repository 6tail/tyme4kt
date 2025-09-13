package com.tyme.test

import com.tyme.eightchar.ChildLimit
import com.tyme.eightchar.DecadeFortune
import com.tyme.eightchar.EightChar
import com.tyme.eightchar.Fortune
import com.tyme.eightchar.provider.impl.*
import com.tyme.enums.Gender
import com.tyme.lunar.LunarHour
import com.tyme.sixtycycle.HeavenStem
import com.tyme.sixtycycle.SixtyCycle
import com.tyme.solar.SolarTime
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

/**
 * 八字测试
 *
 * @author 6tail
 */
class EightCharTest {
    /** 十神 */
    @Test
    fun test1() {
        // 八字
        val eightChar = EightChar("丙寅", "癸巳", "癸酉", "己未")

        // 年柱
        val year: SixtyCycle = eightChar.getYear()
        // 月柱
        val month: SixtyCycle = eightChar.getMonth()
        // 日柱
        val day: SixtyCycle = eightChar.getDay()
        // 时柱
        val hour: SixtyCycle = eightChar.getHour()

        // 日元(日主、日干)
        val me: HeavenStem = day.getHeavenStem()

        // 年柱天干十神
        assertEquals("正财", me.getTenStar(year.getHeavenStem()).getName())
        // 月柱天干十神
        assertEquals("比肩", me.getTenStar(month.getHeavenStem()).getName())
        // 时柱天干十神
        assertEquals("七杀", me.getTenStar(hour.getHeavenStem()).getName())

        // 年柱地支十神（本气)
        assertEquals("伤官", me.getTenStar(year.getEarthBranch().getHideHeavenStemMain()).getName())
        // 年柱地支十神（中气)
        assertEquals("正财", year.getEarthBranch().getHideHeavenStemMiddle()?.let { me.getTenStar(it).getName() })
        // 年柱地支十神（余气)
        assertEquals("正官", year.getEarthBranch().getHideHeavenStemResidual()?.let { me.getTenStar(it).getName() })

        // 日柱地支十神（本气)
        assertEquals("偏印", me.getTenStar(day.getEarthBranch().getHideHeavenStemMain()).getName())
        // 日柱地支藏干（中气)
        assertNull(day.getEarthBranch().getHideHeavenStemMiddle())
        // 日柱地支藏干（余气)
        assertNull(day.getEarthBranch().getHideHeavenStemResidual())

        // 指定任意天干的十神
        assertEquals("正财", me.getTenStar(HeavenStem("丙")).getName())
    }

    /**
     * 地势(长生十二神)
     */
    @Test
    fun test2() {
        // 八字
        val eightChar = EightChar("丙寅", "癸巳", "癸酉", "己未")

        // 年柱
        val year: SixtyCycle = eightChar.getYear()
        // 月柱
        val month: SixtyCycle = eightChar.getMonth()
        // 日柱
        val day: SixtyCycle = eightChar.getDay()
        // 时柱
        val hour: SixtyCycle = eightChar.getHour()

        // 日元(日主、日干)
        val me: HeavenStem = day.getHeavenStem()

        // 年柱地势
        assertEquals("沐浴", me.getTerrain(year.getEarthBranch()).getName())
        // 月柱地势
        assertEquals("胎", me.getTerrain(month.getEarthBranch()).getName())
        // 日柱地势
        assertEquals("病", me.getTerrain(day.getEarthBranch()).getName())
        // 时柱地势
        assertEquals("墓", me.getTerrain(hour.getEarthBranch()).getName())
    }

    /**
     * 胎元/胎息/命宫
     */
    @Test
    fun test3() {
        // 八字
        val eightChar = EightChar("癸卯", "辛酉", "己亥", "癸酉")

        // 胎元
        val taiYuan: SixtyCycle = eightChar.getFetalOrigin()
        assertEquals("壬子", taiYuan.getName())
        // 胎元纳音
        assertEquals("桑柘木", taiYuan.getSound().getName())
    }

    /**
     * 胎息
     */
    @Test
    fun test4() {
        // 八字
        val eightChar = EightChar("癸卯", "辛酉", "己亥", "癸酉")

        // 胎息
        val taiXi: SixtyCycle = eightChar.getFetalBreath()
        assertEquals("甲寅", taiXi.getName())
        // 胎息纳音
        assertEquals("大溪水", taiXi.getSound().getName())
    }

    /**
     * 命宫
     */
    @Test
    fun test5() {
        // 八字
        val eightChar = EightChar("癸卯", "辛酉", "己亥", "癸酉")

        // 命宫
        val mingGong: SixtyCycle = eightChar.getOwnSign()
        assertEquals("癸亥", mingGong.getName())
        // 命宫纳音
        assertEquals("大海水", mingGong.getSound().getName())
    }

    /**
     * 身宫
     */
    @Test
    fun test6() {
        // 八字
        val eightChar = EightChar("癸卯", "辛酉", "己亥", "癸酉")

        // 身宫
        val shenGong: SixtyCycle = eightChar.getBodySign()
        assertEquals("己未", shenGong.getName())
        // 身宫纳音
        assertEquals("天上火", shenGong.getSound().getName())
    }

    /**
     * 地势(长生十二神)
     */
    @Test
    fun test7() {
        // 八字
        val eightChar = EightChar("乙酉", "戊子", "辛巳", "壬辰")

        // 日干
        val me: HeavenStem = eightChar.getDay().getHeavenStem()
        // 年柱地势
        assertEquals("临官", me.getTerrain(eightChar.getYear().getEarthBranch()).getName())
        // 月柱地势
        assertEquals("长生", me.getTerrain(eightChar.getMonth().getEarthBranch()).getName())
        // 日柱地势
        assertEquals("死", me.getTerrain(eightChar.getDay().getEarthBranch()).getName())
        // 时柱地势
        assertEquals("墓", me.getTerrain(eightChar.getHour().getEarthBranch()).getName())
    }

    /**
     * 公历时刻转八字
     */
    @Test
    fun test8() {
        val eightChar = SolarTime(2005, 12, 23, 8, 37, 0).getLunarHour().getEightChar()
        assertEquals("乙酉", eightChar.getYear().getName())
        assertEquals("戊子", eightChar.getMonth().getName())
        assertEquals("辛巳", eightChar.getDay().getName())
        assertEquals("壬辰", eightChar.getHour().getName())
    }

    @Test
    fun test9() {
        val eightChar = SolarTime(1988, 2, 15, 23, 30, 0).getLunarHour().getEightChar()
        assertEquals("戊辰", eightChar.getYear().getName())
        assertEquals("甲寅", eightChar.getMonth().getName())
        assertEquals("辛丑", eightChar.getDay().getName())
        assertEquals("戊子", eightChar.getHour().getName())
    }

    /**
     * 童限测试
     */
    @Test
    fun test11() {
        val childLimit = ChildLimit(SolarTime(2022, 3, 9, 20, 51, 0), Gender.MAN)
        assertEquals(8, childLimit.getYearCount())
        assertEquals(9, childLimit.getMonthCount())
        assertEquals(2, childLimit.getDayCount())
        assertEquals(10, childLimit.getHourCount())
        assertEquals(6, childLimit.getMinuteCount())
        assertEquals("2030年12月12日 06:57:00", childLimit.getEndTime().toString())
    }

    /**
     * 童限测试
     */
    @Test
    fun test12() {
        val childLimit = ChildLimit(SolarTime(2018, 6, 11, 9, 30, 0), Gender.WOMAN)
        assertEquals(1, childLimit.getYearCount())
        assertEquals(9, childLimit.getMonthCount())
        assertEquals(10, childLimit.getDayCount())
        assertEquals(1, childLimit.getHourCount())
        assertEquals(52, childLimit.getMinuteCount())
        assertEquals("2020年3月21日 11:22:00", childLimit.getEndTime().toString())
    }

    /**
     * 大运测试
     */
    @Test
    fun test13() {
        // 童限
        val childLimit = ChildLimit(SolarTime(1983, 2, 15, 20, 0, 0), Gender.WOMAN)
        // 八字
        assertEquals("癸亥 甲寅 甲戌 甲戌", childLimit.getEightChar().toString())
        // 童限年数
        assertEquals(6, childLimit.getYearCount())
        // 童限月数
        assertEquals(2, childLimit.getMonthCount())
        // 童限日数
        assertEquals(18, childLimit.getDayCount())
        // 童限结束(即开始起运)的公历时刻
        assertEquals("1989年5月4日 18:24:00", childLimit.getEndTime().toString())
        // 童限开始(即出生)的农历年干支
        assertEquals("癸亥", childLimit.getStartTime().getLunarHour().getLunarDay().getLunarMonth().getLunarYear().getSixtyCycle().getName())
        // 童限结束(即开始起运)的农历年干支
        assertEquals("己巳", childLimit.getEndTime().getLunarHour().getLunarDay().getLunarMonth().getLunarYear().getSixtyCycle().getName())

        // 第1轮大运
        val decadeFortune: DecadeFortune = childLimit.getStartDecadeFortune()
        // 开始年龄
        assertEquals(7, decadeFortune.getStartAge())
        // 结束年龄
        assertEquals(16, decadeFortune.getEndAge())
        // 开始年
        assertEquals(1989, decadeFortune.getStartSixtyCycleYear().getYear())
        // 结束年
        assertEquals(1998, decadeFortune.getEndSixtyCycleYear().getYear())
        // 干支
        assertEquals("乙卯", decadeFortune.getName())
        // 下一大运
        assertEquals("丙辰", decadeFortune.next(1).getName())
        // 上一大运
        assertEquals("甲寅", decadeFortune.next(-1).getName())
        // 第9轮大运
        assertEquals("癸亥", decadeFortune.next(8).getName())

        // 小运
        val fortune: Fortune = childLimit.getStartFortune()
        // 年龄
        assertEquals(7, fortune.getAge())
        // 干支年
        assertEquals(1989, fortune.getSixtyCycleYear().getYear())
        // 干支
        assertEquals("辛巳", fortune.getName())

        // 流年
        assertEquals("己巳", fortune.getSixtyCycleYear().getSixtyCycle().getName())
    }

    @Test
    fun test14() {
        // 童限
        val childLimit = ChildLimit(SolarTime(1992, 2, 2, 12, 0, 0), Gender.MAN)
        // 八字
        assertEquals("辛未 辛丑 戊申 戊午", childLimit.getEightChar().toString())
        // 童限年数
        assertEquals(9, childLimit.getYearCount())
        // 童限月数
        assertEquals(0, childLimit.getMonthCount())
        // 童限日数
        assertEquals(9, childLimit.getDayCount())
        // 童限结束(即开始起运)的公历时刻
        assertEquals("2001年2月11日 18:58:00", childLimit.getEndTime().toString())
        // 童限开始(即出生)的农历年干支
        assertEquals("辛未", childLimit.getStartTime().getLunarHour().getLunarDay().getLunarMonth().getLunarYear().getSixtyCycle().getName())
        // 童限结束(即开始起运)的农历年干支
        assertEquals("辛巳", childLimit.getEndTime().getLunarHour().getLunarDay().getLunarMonth().getLunarYear().getSixtyCycle().getName())

        // 第1轮大运
        val decadeFortune: DecadeFortune = childLimit.getStartDecadeFortune()
        // 开始年龄
        assertEquals(10, decadeFortune.getStartAge())
        // 结束年龄
        assertEquals(19, decadeFortune.getEndAge())
        // 开始年
        assertEquals(2001, decadeFortune.getStartSixtyCycleYear().getYear())
        // 结束年
        assertEquals(2010, decadeFortune.getEndSixtyCycleYear().getYear())
        // 干支
        assertEquals("庚子", decadeFortune.getName())
        // 下一大运
        assertEquals("己亥", decadeFortune.next(1).getName())

        // 小运
        val fortune: Fortune = childLimit.getStartFortune()
        // 年龄
        assertEquals(10, fortune.getAge())
        // 干支年
        assertEquals(2001, fortune.getSixtyCycleYear().getYear())
        // 干支
        assertEquals("戊申", fortune.getName())
        // 小运推移
        assertEquals("丙午", fortune.next(2).getName())
        assertEquals("庚戌", fortune.next(-2).getName())

        // 流年
        assertEquals("辛巳", fortune.getSixtyCycleYear().getSixtyCycle().getName())
    }

    /**
     * 排盘示例
     */
    @Test
    fun test15() {
        val eightChar = EightChar("丙寅", "癸巳", "癸酉", "己未")
        val year: SixtyCycle = eightChar.getYear()
        val month: SixtyCycle = eightChar.getMonth()
        val day: SixtyCycle = eightChar.getDay()
        val hour: SixtyCycle = eightChar.getHour()

        val me: HeavenStem = day.getHeavenStem()

        fun formatAndPrintln(template: String, vararg args: Any?): String = args
            .fold(template) { acc, e -> acc.replaceFirst("%s", e.toString()) }
            .replace("%n", "\n")
            .also(::print)

        formatAndPrintln(
            "主星：%s %s 日主 %s%n",
            me.getTenStar(year.getHeavenStem()),
            me.getTenStar(month.getHeavenStem()),
            me.getTenStar(hour.getHeavenStem())
        )
        formatAndPrintln(
            "八字：%s %s %s %s%n",
            year,
            month,
            day,
            hour
        )
        formatAndPrintln(
            "藏干：[%s %s %s] [%s %s %s] [%s %s %s] [%s %s %s]%n",
            year.getEarthBranch().getHideHeavenStemMain(),
            year.getEarthBranch().getHideHeavenStemMiddle(),
            year.getEarthBranch().getHideHeavenStemResidual(),
            month.getEarthBranch().getHideHeavenStemMain(),
            month.getEarthBranch().getHideHeavenStemMiddle(),
            month.getEarthBranch().getHideHeavenStemResidual(),
            day.getEarthBranch().getHideHeavenStemMain(),
            day.getEarthBranch().getHideHeavenStemMiddle(),
            day.getEarthBranch().getHideHeavenStemResidual(),
            hour.getEarthBranch().getHideHeavenStemMain(),
            hour.getEarthBranch().getHideHeavenStemMiddle(),
            hour.getEarthBranch().getHideHeavenStemResidual()
        )
        formatAndPrintln(
            "副星：[%s %s %s] [%s %s %s] [%s %s %s] [%s %s %s]%n",
            me.getTenStar(year.getEarthBranch().getHideHeavenStemMain()),
            year.getEarthBranch().getHideHeavenStemMiddle()?.let { me.getTenStar(it) },
            year.getEarthBranch().getHideHeavenStemResidual()?.let { me.getTenStar(it) },

            me.getTenStar(month.getEarthBranch().getHideHeavenStemMain()),
            month.getEarthBranch().getHideHeavenStemMiddle()?.let { me.getTenStar(it) },
            month.getEarthBranch().getHideHeavenStemResidual()?.let { me.getTenStar(it) },

            me.getTenStar(day.getEarthBranch().getHideHeavenStemMain()),
            day.getEarthBranch().getHideHeavenStemMiddle()?.let { me.getTenStar(it) },
            day.getEarthBranch().getHideHeavenStemResidual()?.let { me.getTenStar(it) },

            me.getTenStar(hour.getEarthBranch().getHideHeavenStemMain()),
            hour.getEarthBranch().getHideHeavenStemMiddle()?.let { me.getTenStar(it) },
            hour.getEarthBranch().getHideHeavenStemResidual()?.let { me.getTenStar(it) }
        )
        formatAndPrintln(
            "五行：%s%s %s%s %s%s %s%s%n",
            year.getHeavenStem().getElement(),
            year.getEarthBranch().getElement(),
            month.getHeavenStem().getElement(),
            month.getEarthBranch().getElement(),
            day.getHeavenStem().getElement(),
            day.getEarthBranch().getElement(),
            hour.getHeavenStem().getElement(),
            hour.getEarthBranch().getElement()
        )
        formatAndPrintln(
            "纳音：%s %s %s %s%n",
            year.getSound(),
            month.getSound(),
            day.getSound(),
            hour.getSound()
        )
        formatAndPrintln(
            "星运：%s %s %s %s%n",
            me.getTerrain(year.getEarthBranch()),
            me.getTerrain(month.getEarthBranch()),
            me.getTerrain(day.getEarthBranch()),
            me.getTerrain(hour.getEarthBranch())
        )
        formatAndPrintln(
            "自坐：%s %s %s %s%n",
            year.getHeavenStem().getTerrain(year.getEarthBranch()),
            month.getHeavenStem().getTerrain(month.getEarthBranch()),
            day.getHeavenStem().getTerrain(day.getEarthBranch()),
            hour.getHeavenStem().getTerrain(hour.getEarthBranch())
        )
        formatAndPrintln(
            "空亡：%s %s %s %s%n",
            year.getExtraEarthBranches().contentToString(),
            month.getExtraEarthBranches().contentToString(),
            day.getExtraEarthBranches().contentToString(),
            hour.getExtraEarthBranches().contentToString()
        )

        formatAndPrintln(
            "胎元：%s(%s)%n",
            eightChar.getFetalOrigin(),
            eightChar.getFetalOrigin().getSound()
        )
        formatAndPrintln(
            "胎息：%s(%s)%n",
            eightChar.getFetalBreath(),
            eightChar.getFetalBreath().getSound()
        )
        formatAndPrintln(
            "命宫：%s(%s)%n",
            eightChar.getOwnSign(),
            eightChar.getOwnSign().getSound()
        )
        formatAndPrintln(
            "身宫：%s(%s)%n",
            eightChar.getBodySign(),
            eightChar.getBodySign().getSound()
        )
    }

    @Test
    fun test16() {
        // 童限
        val childLimit =
            ChildLimit(SolarTime(1990, 3, 15, 10, 30, 0), Gender.MAN)
        // 八字
        assertEquals("庚午 己卯 己卯 己巳", childLimit.getEightChar().toString())
        // 童限年数
        assertEquals(6, childLimit.getYearCount())
        // 童限月数
        assertEquals(11, childLimit.getMonthCount())
        // 童限日数
        assertEquals(23, childLimit.getDayCount())
        // 童限结束(即开始起运)的公历时刻
        assertEquals("1997年3月11日 00:22:00", childLimit.getEndTime().toString())

        // 小运
        val fortune: Fortune = childLimit.getStartFortune()
        // 年龄
        assertEquals(8, fortune.getAge())
    }

    @Test
    fun test17() {
        assertEquals("丁丑", EightChar("己丑", "戊辰", "戊辰", "甲子").getOwnSign().getName())
    }

    @Test
    fun test18() {
        assertEquals("乙卯", EightChar("戊戌", "庚申", "丁亥", "丙午").getOwnSign().getName())
    }

    @Test
    fun test19() {
        assertEquals("甲戌", EightChar(SixtyCycle("甲子"), SixtyCycle("壬申"), SixtyCycle("甲子"), SixtyCycle("乙亥")).getOwnSign().getName())
    }

    @Test
    fun test20() {
        val eightChar = ChildLimit(SolarTime(2024, 1, 29, 9, 33, 0), Gender.MAN).getEightChar()
        assertEquals("癸卯 乙丑 壬辰 乙巳", eightChar.toString())
        assertEquals("癸亥", eightChar.getOwnSign().getName())
        assertEquals("己未", eightChar.getBodySign().getName())
    }

    @Test
    fun test21() {
        assertEquals("庚子", EightChar(SixtyCycle("辛亥"), SixtyCycle("乙未"), SixtyCycle("甲子"), SixtyCycle("甲辰")).getBodySign().getName())
    }

    @Test
    fun test22() {
        assertEquals("丙寅", ChildLimit(SolarTime(1990, 1, 27, 0, 0, 0), Gender.MAN).getEightChar().getBodySign().getName())
    }

    @Test
    fun test23() {
        assertEquals("甲戌", ChildLimit(SolarTime(2019, 3, 7, 8, 0, 0), Gender.MAN).getEightChar().getOwnSign().getName())
    }

    @Test
    fun test24() {
        assertEquals("丁丑", ChildLimit(SolarTime(2019, 3, 27, 2, 0, 0), Gender.MAN).getEightChar().getOwnSign().getName())
    }

    @Test
    fun test25() {
        assertEquals("丙寅", LunarHour(1994, 5, 20, 18, 0, 0).getEightChar().getOwnSign().getName())
    }

    @Test
    fun test26() {
        val eightChar = SolarTime(1986, 5, 29, 13, 37, 0).getLunarHour().getEightChar()
        assertEquals("丙寅 癸巳 癸酉 己未", eightChar.toString())
        assertEquals("癸巳", eightChar.getOwnSign().getName())
        assertEquals("辛丑", eightChar.getBodySign().getName())
        assertEquals("甲申", eightChar.getFetalOrigin().getName())
        assertEquals("戊辰", eightChar.getFetalBreath().getName())
    }

    @Test
    fun test27() {
        val eightChar = SolarTime(1994, 12, 6, 2, 0, 0).getLunarHour().getEightChar()
        assertEquals("甲戌 乙亥 丙寅 己丑", eightChar.toString())
        assertEquals("己巳", eightChar.getOwnSign().getName())
        assertEquals("丁丑", eightChar.getBodySign().getName())
        assertEquals("丙寅", eightChar.getFetalOrigin().getName())
        assertEquals("辛亥", eightChar.getFetalBreath().getName())
    }

    @Test
    fun test28() {
        assertEquals("辛卯", EightChar("辛亥", "丁酉", "丙午", "癸巳").getOwnSign().getName())
    }

    @Test
    fun test29() {
        val eightChar = EightChar("丙寅", "庚寅", "辛卯", "壬辰")
        assertEquals("己亥", eightChar.getOwnSign().getName())
        assertEquals("乙未", eightChar.getBodySign().getName())
    }

    @Test
    fun test30() {
        assertEquals("乙巳", EightChar("壬子", "辛亥", "壬戌", "乙巳").getBodySign().getName())
    }

    @Test
    fun test31() {
        val solarTimes: List<SolarTime> = EightChar("丙辰", "丁酉", "丙子", "甲午").getSolarTimes(1900, 2024)
        val actual: MutableList<String> = mutableListOf()
        for (solarTime in solarTimes) {
            actual.add(solarTime.toString())
        }

        val expected: MutableList<String> = mutableListOf()
        expected.add("1916年10月6日 12:00:00")
        expected.add("1976年9月21日 12:00:00")
        assertEquals(expected, actual)
    }

    @Test
    fun test32() {
        val solarTimes: List<SolarTime> = EightChar("壬寅", "庚戌", "己未", "乙亥").getSolarTimes(1900, 2024)
        val actual: MutableList<String> = ArrayList()
        for (solarTime in solarTimes) {
            actual.add(solarTime.toString())
        }

        val expected: MutableList<String> = ArrayList()
        expected.add("2022年11月2日 22:00:00")
        assertEquals(expected, actual)
    }

    @Test
    fun test33() {
        val solarTimes: List<SolarTime> = EightChar("己卯", "辛未", "甲戌", "壬申").getSolarTimes(1900, 2024)
        val actual: MutableList<String> = ArrayList()
        for (solarTime in solarTimes) {
            actual.add(solarTime.toString())
        }

        val expected: MutableList<String> = ArrayList()
        expected.add("1939年8月5日 16:00:00")
        expected.add("1999年7月21日 16:00:00")
        assertEquals(expected, actual)
    }

    @Test
    fun test34() {
        val solarTimes: List<SolarTime> = EightChar("庚子", "戊子", "己卯", "庚午").getSolarTimes(1900, 2024)
        val actual: MutableList<String> = ArrayList()
        for (solarTime in solarTimes) {
            actual.add(solarTime.toString())
        }

        val expected: MutableList<String> = ArrayList()
        expected.add("1901年1月1日 12:00:00")
        expected.add("1960年12月17日 12:00:00")
        assertEquals(expected, actual)
    }

    @Test
    fun test35() {
        val solarTimes: List<SolarTime> = EightChar("庚子", "癸未", "乙丑", "丁亥").getSolarTimes(1900, 2024)
        val actual: MutableList<String> = ArrayList()
        for (solarTime in solarTimes) {
            actual.add(solarTime.toString())
        }

        val expected: MutableList<String> = ArrayList()
        expected.add("1960年8月5日 22:00:00")
        expected.add("2020年7月21日 22:00:00")
        assertEquals(expected, actual)
    }

    @Test
    fun test36() {
        val solarTimes: List<SolarTime> = EightChar("癸卯", "甲寅", "甲寅", "甲子").getSolarTimes(1800, 2024)
        val actual: MutableList<String> = ArrayList()
        for (solarTime in solarTimes) {
            actual.add(solarTime.toString())
        }

        val expected: MutableList<String> = ArrayList()
        expected.add("1843年2月9日 00:00:00")
        expected.add("2023年2月25日 00:00:00")
        assertEquals(expected, actual)
    }

    @Test
    fun test37() {
        val solarTimes: List<SolarTime> = EightChar("甲辰", "丙寅", "己亥", "戊辰").getSolarTimes(1800, 2024)
        val actual: MutableList<String> = ArrayList()
        for (solarTime in solarTimes) {
            actual.add(solarTime.toString())
        }

        val expected: MutableList<String> = ArrayList()
        expected.add("1904年3月6日 07:00:00")
        expected.add("1964年2月20日 08:00:00")
        expected.add("2024年2月5日 08:00:00")
        assertEquals(expected, actual)
    }

    @Test
    fun test38() {
        val solarTimes: List<SolarTime> = EightChar("己亥", "丁丑", "壬寅", "戊申").getSolarTimes(1900, 2024)
        val actual: MutableList<String> = ArrayList()
        for (solarTime in solarTimes) {
            actual.add(solarTime.toString())
        }

        val expected: MutableList<String> = ArrayList()
        expected.add("1900年1月29日 16:00:00")
        expected.add("1960年1月15日 16:00:00")
        assertEquals(expected, actual)
    }

    @Test
    fun test39() {
        val solarTimes: List<SolarTime> = EightChar("己亥", "丙子", "癸酉", "庚申").getSolarTimes(1900, 2024)
        val actual: MutableList<String> = ArrayList()
        for (solarTime in solarTimes) {
            actual.add(solarTime.toString())
        }

        val expected: MutableList<String> = ArrayList()
        expected.add("1959年12月17日 16:00:00")
        assertEquals(expected, actual)
    }

    @Test
    fun test40() {
        val solarTimes: List<SolarTime> = EightChar("丁丑", "癸卯", "癸丑", "辛酉").getSolarTimes(1900, 2024)
        val actual: MutableList<String> = ArrayList()
        for (solarTime in solarTimes) {
            actual.add(solarTime.toString())
        }

        val expected: MutableList<String> = ArrayList()
        expected.add("1937年3月27日 18:00:00")
        expected.add("1997年3月12日 18:00:00")
        assertEquals(expected, actual)
    }

    @Test
    fun test41() {
        val solarTimes: List<SolarTime> = EightChar("乙未", "己卯", "丁丑", "甲辰").getSolarTimes(1900, 2024)
        val actual: MutableList<String> = ArrayList()
        for (solarTime in solarTimes) {
            actual.add(solarTime.toString())
        }

        val expected: MutableList<String> = ArrayList()
        expected.add("1955年3月17日 08:00:00")
        assertEquals(expected, actual)
    }

    @Test
    fun test42() {
        assertEquals("壬申", EightChar("甲辰", "丙寅", "己亥", "辛未").getOwnSign().getName())
    }

    @Test
    fun test43() {
        // 采用元亨利贞的起运算法
        ChildLimit.provider = China95ChildLimitProvider()
        // 童限
        val childLimit =
            ChildLimit(SolarTime(1986, 5, 29, 13, 37, 0), Gender.MAN)
        // 童限年数
        assertEquals(2, childLimit.getYearCount())
        // 童限月数
        assertEquals(7, childLimit.getMonthCount())
        // 童限日数
        assertEquals(0, childLimit.getDayCount())
        // 童限时数
        assertEquals(0, childLimit.getHourCount())
        // 童限分数
        assertEquals(0, childLimit.getMinuteCount())
        // 童限结束(即开始起运)的公历时刻
        assertEquals("1988年12月29日 13:37:00", childLimit.getEndTime().toString())

        // 为了不影响其他测试用例，恢复默认起运算法
        ChildLimit.provider = DefaultChildLimitProvider()
    }

    @Test
    fun test44() {
        // 童限
        val childLimit = ChildLimit(SolarTime(1989, 12, 31, 23, 7, 17), Gender.MAN)
        // 童限结束(即开始起运)的公历时刻
        assertEquals("1998年3月1日 19:47:17", childLimit.getEndTime().toString())
    }

    @Test
    fun test45() {
        // 童限
        ChildLimit.provider = LunarSect1ChildLimitProvider()

        val childLimit = ChildLimit(SolarTime(1994, 10, 17, 1, 0, 0), Gender.MAN)
        assertEquals("2002年1月27日 01:00:00", childLimit.getEndTime().toString())
        assertEquals("壬午", childLimit.getStartDecadeFortune().getStartSixtyCycleYear().getSixtyCycle().getName())

        // 为了不影响其他测试用例，恢复默认起运算法
        ChildLimit.provider = DefaultChildLimitProvider()
    }

    @Test
    fun test46() {
        LunarHour.provider = LunarSect2EightCharProvider()
        val solarTimes: List<SolarTime> = EightChar("壬寅", "丙午", "己亥", "丙子").getSolarTimes(1900, 2024)
        val actual: MutableList<String> = ArrayList()
        for (solarTime in solarTimes) {
            actual.add(solarTime.toString())
        }

        val expected: MutableList<String> = ArrayList()
        expected.add("1962年6月30日 23:00:00")
        expected.add("2022年6月15日 23:00:00")
        assertEquals(expected, actual)
        LunarHour.provider = DefaultEightCharProvider()
    }

    @Test
    fun test48() {
        // 童限
        ChildLimit.provider = LunarSect1ChildLimitProvider()

        val childLimit = ChildLimit(SolarTime(2025, 2, 18, 16, 0, 0), Gender.MAN)
        assertEquals("甲寅", childLimit.getStartFortune().getSixtyCycle().getName())
        assertEquals("2030年1月18日 16:00:00", childLimit.getEndTime().toString())
        assertEquals("庚戌", childLimit.getEndSixtyCycleYear().getSixtyCycle().getName())
        assertEquals("庚戌", childLimit.getStartFortune().getSixtyCycleYear().getSixtyCycle().getName())
        // 为了不影响其他测试用例，恢复默认起运算法
        ChildLimit.provider = DefaultChildLimitProvider()
    }

    @Test
    fun test49() {
        val eightChar = SolarTime(1980, 6, 15, 12, 30, 30).getLunarHour().getEightChar()
        assertEquals("辛巳", eightChar.getOwnSign().getName())
        assertEquals("己丑", eightChar.getBodySign().getName())
        assertEquals("癸酉", eightChar.getFetalOrigin().getName())
        assertEquals("甲午", eightChar.getFetalBreath().getName())
    }

    @Test
    fun test50() {
        val solarTimes: List<SolarTime> = EightChar("壬申", "壬寅", "庚辰", "甲申").getSolarTimes(1801, 2099)
        val actual: MutableList<String> = ArrayList()
        for (solarTime in solarTimes) {
            actual.add(solarTime.toString())
        }

        val expected: MutableList<String> = ArrayList()
        expected.add("1812年2月18日 16:00:00")
        expected.add("1992年3月5日 15:00:00")
        expected.add("2052年2月19日 16:00:00")
        assertEquals(expected, actual)
    }
}

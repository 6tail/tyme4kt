package com.tyme.test

import com.tyme.sixtycycle.HeavenStem
import kotlin.test.*

/**
 * 天干测试
 *
 * @author 6tail
 */
class HeavenlyStemTest {
    @Test
    fun test0() {
        assertEquals("甲", HeavenStem(0).getName())
    }

    @Test
    fun test1() {
        assertEquals(0, HeavenStem("甲").getIndex())
    }

    /** 天干的五行生克 */
    @Test
    fun test2() {
        assertEquals(HeavenStem("丙").getElement(), HeavenStem("甲").getElement().getReinforce())
    }

    /** 十神  */
    @Test
    fun test3() {
        val SHI_SHEN = mapOf(
            "甲甲" to "比肩",
            "甲乙" to "劫财",
            "甲丙" to "食神",
            "甲丁" to "伤官",
            "甲戊" to "偏财",
            "甲己" to "正财",
            "甲庚" to "七杀",
            "甲辛" to "正官",
            "甲壬" to "偏印",
            "甲癸" to "正印",
            "乙乙" to "比肩",
            "乙甲" to "劫财",
            "乙丁" to "食神",
            "乙丙" to "伤官",
            "乙己" to "偏财",
            "乙戊" to "正财",
            "乙辛" to "七杀",
            "乙庚" to "正官",
            "乙癸" to "偏印",
            "乙壬" to "正印",
            "丙丙" to "比肩",
            "丙丁" to "劫财",
            "丙戊" to "食神",
            "丙己" to "伤官",
            "丙庚" to "偏财",
            "丙辛" to "正财",
            "丙壬" to "七杀",
            "丙癸" to "正官",
            "丙甲" to "偏印",
            "丙乙" to "正印",
            "丁丁" to "比肩",
            "丁丙" to "劫财",
            "丁己" to "食神",
            "丁戊" to "伤官",
            "丁辛" to "偏财",
            "丁庚" to "正财",
            "丁癸" to "七杀",
            "丁壬" to "正官",
            "丁乙" to "偏印",
            "丁甲" to "正印",
            "戊戊" to "比肩",
            "戊己" to "劫财",
            "戊庚" to "食神",
            "戊辛" to "伤官",
            "戊壬" to "偏财",
            "戊癸" to "正财",
            "戊甲" to "七杀",
            "戊乙" to "正官",
            "戊丙" to "偏印",
            "戊丁" to "正印",
            "己己" to "比肩",
            "己戊" to "劫财",
            "己辛" to "食神",
            "己庚" to "伤官",
            "己癸" to "偏财",
            "己壬" to "正财",
            "己乙" to "七杀",
            "己甲" to "正官",
            "己丁" to "偏印",
            "己丙" to "正印",
            "庚庚" to "比肩",
            "庚辛" to "劫财",
            "庚壬" to "食神",
            "庚癸" to "伤官",
            "庚甲" to "偏财",
            "庚乙" to "正财",
            "庚丙" to "七杀",
            "庚丁" to "正官",
            "庚戊" to "偏印",
            "庚己" to "正印",
            "辛辛" to "比肩",
            "辛庚" to "劫财",
            "辛癸" to "食神",
            "辛壬" to "伤官",
            "辛乙" to "偏财",
            "辛甲" to "正财",
            "辛丁" to "七杀",
            "辛丙" to "正官",
            "辛己" to "偏印",
            "辛戊" to "正印",
            "壬壬" to "比肩",
            "壬癸" to "劫财",
            "壬甲" to "食神",
            "壬乙" to "伤官",
            "壬丙" to "偏财",
            "壬丁" to "正财",
            "壬戊" to "七杀",
            "壬己" to "正官",
            "壬庚" to "偏印",
            "壬辛" to "正印",
            "癸癸" to "比肩",
            "癸壬" to "劫财",
            "癸乙" to "食神",
            "癸甲" to "伤官",
            "癸丁" to "偏财",
            "癸丙" to "正财",
            "癸己" to "七杀",
            "癸戊" to "正官",
            "癸辛" to "偏印",
            "癸庚" to "正印",
        )
        for ((gz, value) in SHI_SHEN) {
            assertEquals(value,
                gz.let {
                    HeavenStem(it.substring(0, 1))
                        .getTenStar(HeavenStem(gz.substring(1))).getName()
                }
            )
        }
    }

    /** 天干五合 */
    @Test
    fun test4() {
        assertEquals("乙", HeavenStem("庚").getCombine().getName())
        assertEquals("庚", HeavenStem("乙").getCombine().getName())
        assertEquals("土", HeavenStem("甲").combine(HeavenStem("己"))?.getName())
        assertEquals("土", HeavenStem("己").combine(HeavenStem("甲"))?.getName())
        assertEquals("木", HeavenStem("丁").combine(HeavenStem("壬"))?.getName())
        assertEquals("木", HeavenStem("壬").combine(HeavenStem("丁"))?.getName())
        assertNull(HeavenStem("甲").combine(HeavenStem("乙")))
    }
}

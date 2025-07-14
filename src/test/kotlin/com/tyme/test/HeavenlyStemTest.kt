package com.tyme.test

import com.tyme.sixtycycle.HeavenStem
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

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
        val SHI_SHEN: HashMap<String?, String?> = object : HashMap<String?, String?>() {
            private val serialVersionUID: Long = -1

            init {
                put("甲甲", "比肩")
                put("甲乙", "劫财")
                put("甲丙", "食神")
                put("甲丁", "伤官")
                put("甲戊", "偏财")
                put("甲己", "正财")
                put("甲庚", "七杀")
                put("甲辛", "正官")
                put("甲壬", "偏印")
                put("甲癸", "正印")
                put("乙乙", "比肩")
                put("乙甲", "劫财")
                put("乙丁", "食神")
                put("乙丙", "伤官")
                put("乙己", "偏财")
                put("乙戊", "正财")
                put("乙辛", "七杀")
                put("乙庚", "正官")
                put("乙癸", "偏印")
                put("乙壬", "正印")
                put("丙丙", "比肩")
                put("丙丁", "劫财")
                put("丙戊", "食神")
                put("丙己", "伤官")
                put("丙庚", "偏财")
                put("丙辛", "正财")
                put("丙壬", "七杀")
                put("丙癸", "正官")
                put("丙甲", "偏印")
                put("丙乙", "正印")
                put("丁丁", "比肩")
                put("丁丙", "劫财")
                put("丁己", "食神")
                put("丁戊", "伤官")
                put("丁辛", "偏财")
                put("丁庚", "正财")
                put("丁癸", "七杀")
                put("丁壬", "正官")
                put("丁乙", "偏印")
                put("丁甲", "正印")
                put("戊戊", "比肩")
                put("戊己", "劫财")
                put("戊庚", "食神")
                put("戊辛", "伤官")
                put("戊壬", "偏财")
                put("戊癸", "正财")
                put("戊甲", "七杀")
                put("戊乙", "正官")
                put("戊丙", "偏印")
                put("戊丁", "正印")
                put("己己", "比肩")
                put("己戊", "劫财")
                put("己辛", "食神")
                put("己庚", "伤官")
                put("己癸", "偏财")
                put("己壬", "正财")
                put("己乙", "七杀")
                put("己甲", "正官")
                put("己丁", "偏印")
                put("己丙", "正印")
                put("庚庚", "比肩")
                put("庚辛", "劫财")
                put("庚壬", "食神")
                put("庚癸", "伤官")
                put("庚甲", "偏财")
                put("庚乙", "正财")
                put("庚丙", "七杀")
                put("庚丁", "正官")
                put("庚戊", "偏印")
                put("庚己", "正印")
                put("辛辛", "比肩")
                put("辛庚", "劫财")
                put("辛癸", "食神")
                put("辛壬", "伤官")
                put("辛乙", "偏财")
                put("辛甲", "正财")
                put("辛丁", "七杀")
                put("辛丙", "正官")
                put("辛己", "偏印")
                put("辛戊", "正印")
                put("壬壬", "比肩")
                put("壬癸", "劫财")
                put("壬甲", "食神")
                put("壬乙", "伤官")
                put("壬丙", "偏财")
                put("壬丁", "正财")
                put("壬戊", "七杀")
                put("壬己", "正官")
                put("壬庚", "偏印")
                put("壬辛", "正印")
                put("癸癸", "比肩")
                put("癸壬", "劫财")
                put("癸乙", "食神")
                put("癸甲", "伤官")
                put("癸丁", "偏财")
                put("癸丙", "正财")
                put("癸己", "七杀")
                put("癸戊", "正官")
                put("癸辛", "偏印")
                put("癸庚", "正印")
            }
        }
        for ((gz, value) in SHI_SHEN) {
            assertEquals(value,
                gz?.let {
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

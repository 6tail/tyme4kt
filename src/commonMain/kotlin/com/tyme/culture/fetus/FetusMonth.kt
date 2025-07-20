package com.tyme.culture.fetus

import com.tyme.LoopTyme
import com.tyme.lunar.LunarMonth
import kotlin.jvm.JvmStatic

/**
 * 逐月胎神（正十二月在床房，二三九十门户中，四六十一灶勿犯，五甲七子八厕凶。）
 *
 * @author 6tail
 */
class FetusMonth(index: Int): LoopTyme(NAMES, index) {

    override fun next(n: Int): FetusMonth {
        return FetusMonth(nextIndex(n))
    }

    companion object {
        val NAMES: Array<String> = arrayOf("占房床", "占户窗", "占门堂", "占厨灶", "占房床", "占床仓", "占碓磨", "占厕户", "占门房", "占房床", "占灶炉", "占房床")

        /**
         * 从农历月初始化
         *
         * @param lunarMonth 农历月
         * @return 逐月胎神
         */
        @JvmStatic
        fun fromLunarMonth(lunarMonth: LunarMonth): FetusMonth? {
            return if (lunarMonth.isLeap()) null else FetusMonth(lunarMonth.getMonth() - 1)
        }
    }
}

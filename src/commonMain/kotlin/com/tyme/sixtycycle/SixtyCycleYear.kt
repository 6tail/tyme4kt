package com.tyme.sixtycycle

import com.tyme.unit.AbstractTraditionalYear
import kotlin.jvm.JvmStatic

/**
 * 干支年
 *
 * @author 6tail
 */
class SixtyCycleYear(year: Int): AbstractTraditionalYear(year) {

    init {
        validate(year)
    }

    override fun getName(): String {
        return "${getSixtyCycle().getName()}年"
    }

    /**
     * 推移
     *
     * @param n 推移年数
     * @return 干支年
     */
    override fun next(n: Int): SixtyCycleYear {
        return SixtyCycleYear(year + n)
    }

    /**
     * 首月（五虎遁：甲己之年丙作首，乙庚之岁戊为头，丙辛必定寻庚起，丁壬壬位顺行流，若问戊癸何方发，甲寅之上好追求。）
     */
    val firstMonth: SixtyCycleMonth
        get() = SixtyCycleMonth(this, SixtyCycle(year * 12 - 46))

    /**
     * 干支月列表
     *
     * @return 干支月列表
     */
    fun getMonths(): List<SixtyCycleMonth> {
        val l: MutableList<SixtyCycleMonth> = ArrayList(12)
        val m: SixtyCycleMonth = firstMonth
        l.add(m)
        for (i in 1 until 12) {
            l.add(m.next(i))
        }
        return l
    }

    companion object {
        @JvmStatic
        fun validate(year: Int) {
            validateRange(year, -1, 9999, "sixty cycle year")
        }

        /**
         * 从年初始化
         *
         * @param year  年，支持-1到9999年
         * @return 干支年
         */
        @JvmStatic
        fun fromYear(year: Int): SixtyCycleYear {
            return SixtyCycleYear(year)
        }
    }
}

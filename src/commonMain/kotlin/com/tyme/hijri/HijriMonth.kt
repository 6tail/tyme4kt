package com.tyme.hijri

import com.tyme.unit.AbstractDay
import com.tyme.unit.AbstractMonth
import com.tyme.unit.AbstractYear
import kotlin.jvm.JvmStatic

/**
 * 回历月
 *
 * @author 6tail
 */
class HijriMonth(year: Int, month: Int): AbstractMonth(year, month) {

    init {
        validate(year, month)
    }

    /**
     * 回历年
     */
    val hijriYear: HijriYear
        get() = HijriYear(year)

    override val abstractYear: AbstractYear
        get() = hijriYear

    /**
     * 天数（单数月30天，双数月29天，闰年第12月30天)
     *
     * @return 天数
     */
    override val dayCount: Int
        get() {
            var d: Int = if (month.and(1) == 0) 29 else 30
            // 闰年第12月30天
            if (12 == month && hijriYear.isLeap()) {
                d++
            }
            return d
        }

    /**
     * 位于当年的索引(0-11)
     */
    val indexInYear: Int
        get() = month - 1

    override fun getName(): String {
        return NAMES[indexInYear]
    }

    override fun next(n: Int): HijriMonth {
        val i: Int = month - 1 + n
        return HijriMonth((year * 12 + i) / 12, indexOf(i, 12) + 1)
    }

    /**
     * 本月的回历日列表
     *
     * @return 回历日列表
     */
    fun getDays(): List<HijriDay> {
        return buildDays { i ->
            HijriDay(year, month, i)
        }
    }

    override val firstDay: AbstractDay
        get() = HijriDay(year, month, 1)

    companion object {
        val NAMES: Array<String> = arrayOf<String>("穆哈兰姆月", "色法尔月", "赖比尔·敖外鲁月", "赖比尔·阿色尼月", "主马达·敖外鲁月", "主马达·阿色尼月", "赖哲卜月", "舍尔邦月", "赖买丹月", "闪瓦鲁月", "都尔喀尔德月", "都尔黑哲月")

        @JvmStatic
        fun validate(year: Int, month: Int) {
            validateRange(month, 1, 12, "hijri month")
            HijriYear.validate(year)
        }

        @JvmStatic
        fun fromYm(year: Int, month: Int): HijriMonth {
            return HijriMonth(year, month)
        }
    }
}

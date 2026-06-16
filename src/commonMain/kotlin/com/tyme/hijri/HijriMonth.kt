package com.tyme.hijri

import com.tyme.unit.MonthUnit
import kotlin.jvm.JvmStatic

/**
 * 回历月
 *
 * @author 6tail
 */
class HijriMonth(
    year: Int,
    month: Int
) : MonthUnit(year, month) {

    init {
        validate(year, month)
    }

    /**
     * 回历年
     *
     * @return 回历年
     */
    fun getHijriYear(): HijriYear {
        return HijriYear(year)
    }

    /**
     * 天数（单数月30天，双数月29天，闰年第12月30天)
     *
     * @return 天数
     */
    fun getDayCount(): Int {
        var d: Int = if (month % 2 == 0) 29 else 30
        // 闰年第12月30天
        if (12 == month && this.getHijriYear().isLeap()) {
            d++
        }
        return d
    }

    /**
     * 位于当年的索引(0-11)
     *
     * @return 索引
     */
    fun getIndexInYear(): Int {
        return month - 1
    }

    override fun getName(): String {
        return NAMES[getIndexInYear()]
    }

    override fun toString(): String {
        return "${getHijriYear()}${getName()}"
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
        val size: Int = getDayCount()
        val l: MutableList<HijriDay> = ArrayList(size)
        for (i in 1..size) {
            l.add(HijriDay(year, month, i))
        }
        return l
    }

    /**
     * 本月第一天
     *
     * @return 回历日
     */
    fun getFirstDay(): HijriDay {
        return HijriDay(year, month, 1)
    }

    override fun equals(other: Any?): Boolean {
        return other is HijriMonth && toString() == other.toString()
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

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

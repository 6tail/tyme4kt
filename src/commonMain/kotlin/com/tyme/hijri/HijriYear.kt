package com.tyme.hijri

import com.tyme.solar.SolarYear
import com.tyme.unit.YearUnit
import kotlin.jvm.JvmStatic

/**
 * 回历年
 *
 * @author 6tail
 */
class HijriYear(year: Int) : YearUnit(year) {
    init {
        validate(year)
    }

    /**
     * 天数（平年354天，闰年355天）
     *
     * @return 天数
     */
    fun getDayCount(): Int {
        return if (isLeap()) 355 else 354
    }

    /**
     * 是否闰年(1个闰周为30年，1个闰周中第2、5、7、10、13、16、18、21、24、26、29年为闰年)
     *
     * @return true/false
     */
    fun isLeap(): Boolean {
        val i: Int = indexOf(year - 1, 30)
        return i == 1 || i == 4 || i == 6 || i == 9 || i == 12 || i == 15 || i == 17 || i == 20 || i == 23 || i == 25 || i == 28
    }

    override fun getName(): String {
        return "${year}年"
    }

    override fun next(n: Int): SolarYear {
        return SolarYear(year + n)
    }

    /**
     * 月份列表
     *
     * @return 月份列表，1年有12个月。
     */
    fun getMonths(): List<HijriMonth> {
        val l: MutableList<HijriMonth> = ArrayList(12)
        for (i in 1..12) {
            l.add(HijriMonth(year, i))
        }
        return l
    }

    /**
     * 首月
     *
     * @return 回历月
     */
    fun getFirstMonth(): HijriMonth {
        return HijriMonth(year, 1)
    }

    override fun equals(other: Any?): Boolean {
        return other is HijriYear && toString() == other.toString()
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    companion object {
        @JvmStatic
        fun validate(year: Int) {
            validateRange(year, -640, 9666, "hijri year")
        }

        /**
         * 从年初始化
         *
         * @param year 年
         * @return 回历年
         */
        @JvmStatic
        fun fromYear(year: Int): HijriYear {
            return HijriYear(year)
        }
    }
}

package com.tyme.hijri

import com.tyme.unit.AbstractYear
import kotlin.jvm.JvmStatic

/**
 * 回历年
 *
 * @author 6tail
 */
class HijriYear(year: Int): AbstractYear(year) {
    init {
        validate(year)
    }

    /**
     * 天数（平年354天，闰年355天）
     */
    val dayCount: Int
        get() = if (isLeap()) 355 else 354

    /**
     * 是否闰年(1个闰周为30年，1个闰周中第2、5、7、10、13、16、18、21、24、26、29年为闰年)
     *
     * @return true/false
     */
    fun isLeap(): Boolean {
        val i: Int = indexOf(year - 1, 30)
        return i == 1 || i == 4 || i == 6 || i == 9 || i == 12 || i == 15 || i == 17 || i == 20 || i == 23 || i == 25 || i == 28
    }

    override fun next(n: Int): HijriYear {
        return HijriYear(year + n)
    }

    /**
     * 月份列表，1年有12个月。
     *
     * @return 回历月列表
     */
    fun getMonths(): List<HijriMonth> {
        return buildMonths { i ->
            HijriMonth(year, i)
        }
    }

    /**
     * 首月
     */
    val firstMonth: HijriMonth
        get() = HijriMonth(year, 1)

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

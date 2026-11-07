package com.tyme.lunar

import com.tyme.unit.AbstractMonth
import com.tyme.unit.AbstractWeek
import kotlin.jvm.JvmStatic

/**
 * 农历周
 *
 * @author 6tail
 */
class LunarWeek: AbstractWeek {

    /**
     * 初始化
     *
     * @param year  年
     * @param month 月
     * @param index 索引，0-5
     * @param start 起始星期，1234560分别代表星期一至星期天
     */
    constructor(year: Int, month: Int, index: Int, start: Int): super(year, month, index, start) {
        validate(year, month, index, start)
    }

    /**
     * 农历月
     */
    val lunarMonth: LunarMonth
        get() = LunarMonth(year, month)

    override val abstractMonth: AbstractMonth
        get() = lunarMonth

    override fun getName(): String {
        return NAMES[index]
    }

    override fun next(n: Int): LunarWeek {
        val w = super.next(n)
        return LunarWeek(w.year, w.month, w.index, start)
    }

    /**
     * 本周第1天
     */
    val firstDay: LunarDay
        get() {
            val firstDay = LunarDay(year, month, 1)
            return firstDay.next(index * 7 - indexOf(firstDay.getWeek().index - start, 7))
        }

    /**
     * 本周农历日列表
     *
     * @return 农历日列表
     */
    fun getDays(): List<LunarDay> {
        val d: LunarDay = firstDay
        return List(7) { i ->
            d.next(i)
        }
    }

    companion object {
        @JvmStatic
        fun validate(year: Int, month: Int, index: Int, start: Int) {
            validate(index, start)
            val m = LunarMonth(year, month)
            if (index >= m.getWeekCount(start)) {
                throw IllegalArgumentException("illegal lunar week index: $index in month: $m")
            }
        }

        @JvmStatic
        fun fromYm(year: Int, month: Int, index: Int, start: Int): LunarWeek {
            return LunarWeek(year, month, index, start)
        }
    }
}

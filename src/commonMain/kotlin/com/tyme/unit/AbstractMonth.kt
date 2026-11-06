package com.tyme.unit

import kotlin.math.ceil

/**
 * 抽象月
 *
 * @author 6tail
 */
abstract class AbstractMonth(year: Int, month: Int): MonthUnit(year, month) {
    /**
     * 月
     *
     * @return 月，当月为闰月时，返回负数，如-2代表闰二月
     */
    open val monthValue: Int
        get() = month

    /**
     * 周数
     *
     * @param start 起始星期，1234560分别代表星期一至星期天
     * @return 周数
     */
    fun getWeekCount(start: Int): Int {
        return ceil((indexOf(firstDay.getWeek().index - start, 7) + dayCount) / 7.0).toInt()
    }

    abstract override fun next(n: Int): AbstractMonth

    /**
     * 当月天数
     *
     * @return 天数
     */
    open val dayCount: Int
        get() = throw UnsupportedOperationException()

    /**
     * 本月第1天
     *
     * @return 抽象日
     */
    open val firstDay: AbstractDay
        get() = throw UnsupportedOperationException()

    /**
     * 抽象年
     *
     * @return 抽象年
     */
    open val abstractYear: AbstractYear
        get() = throw UnsupportedOperationException()

    override fun toString(): String {
        return abstractYear.toString() + getName()
    }
}

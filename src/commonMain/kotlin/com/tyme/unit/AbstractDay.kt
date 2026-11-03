package com.tyme.unit

import com.tyme.culture.Week

/**
 * 抽象日
 *
 * @author 6tail
 */
abstract class AbstractDay(year: Int, month: Int, day: Int): DayUnit(year, month, day) {
    /**
     * 星期
     *
     * @return 星期
     */
    abstract fun getWeek(): Week
}

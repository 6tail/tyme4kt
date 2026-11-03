package com.tyme.unit

/**
 * 日
 *
 * @author 6tail
 */
abstract class DayUnit(year: Int, month: Int, val day: Int): MonthUnit(year, month) {
    override fun getCompareIndex(): Long {
        return super.getCompareIndex() + day
    }
}

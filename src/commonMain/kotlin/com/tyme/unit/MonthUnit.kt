package com.tyme.unit

/**
 * 月
 *
 * @author 6tail
 */
abstract class MonthUnit(
    /** 年 */
    year: Int,
    /** 月 */
    val month: Int,
) : YearUnit(year) {
    override fun getCompareIndex(): Long {
        return super.getCompareIndex() + (if (month > 0) month * 2L else -month * 2L + 1) * 100
    }
}

package com.tyme.unit

/**
 * 抽象年
 *
 * @author 6tail
 */
abstract class AbstractYear(year: Int): YearUnit(year) {
    /**
     * 月数
     */
    val monthCount: Int
        get() = if (leapMonth < 1) 12 else 13

    /**
     * 闰月(闰月数字，1代表闰1月，0代表无闰月)
     */
    open val leapMonth: Int
        get() = 0

    override fun getName(): String {
        return year.toString() + "年"
    }

    abstract override fun next(n: Int): AbstractYear
}

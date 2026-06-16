package com.tyme.unit

import kotlin.jvm.JvmStatic

/**
 * 秒
 *
 * @author 6tail
 */
abstract class SecondUnit(
    /** 年 */
    year: Int,
    /** 月 */
    month: Int,
    /** 日 */
    day: Int,
    /** 时 */
    val hour: Int,
    /** 分 */
    val minute: Int,
    /** 秒 */
    val second: Int,
) : DayUnit(year, month, day) {
    /**
     * 当天秒数
     *
     * @return 当天秒数
     */
    fun getSecondsInDay(): Int {
        return hour * 3600 + minute * 60 + second
    }

    override fun getCompareIndex(): Long {
        return super.getCompareIndex() * 86400L + getSecondsInDay()
    }

    companion object {
        @JvmStatic
        fun validate(hour: Int, minute: Int, second: Int) {
            validateRange(hour, 0, 23, "hour")
            validateRange(minute, 0, 59, "minute")
            validateRange(second, 0, 59, "second")
        }
    }
}

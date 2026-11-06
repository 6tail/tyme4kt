package com.tyme.unit

import kotlin.math.abs

/**
 * 支持闰月的月份抽象
 *
 * @author 6tail
 */
abstract class AbstractLeapMonth(year: Int, month: Int): AbstractMonth(year, abs(month)) {
    /**
     * 是否闰月
     */
    val leap: Boolean

    init {
        leap = month < 0
    }

    override val monthValue: Int
        get() = if (leap) -month else month

    /**
     * 位于当年的索引(0-12)
     *
     * @return 索引
     */
    val indexInYear: Int
        get() {
            var index: Int = month - 1
            if (leap) {
                index += 1
            } else {
                val leapMonth: Int = abstractYear.leapMonth
                if (leapMonth in 1..<month) {
                    index += 1
                }
            }
            return index
        }

    override fun next(n: Int): AbstractLeapMonth {
        var ty: Int = year
        var tm: Int = monthValue
        if (n != 0) {
            var m = indexInYear + 1 + n
            var y: AbstractYear = abstractYear
            if (n > 0) {
                var monthCount: Int = y.monthCount
                while (m > monthCount) {
                    m -= monthCount
                    y = y.next(1)
                    monthCount = y.monthCount
                }
            } else {
                while (m <= 0) {
                    y = y.next(-1)
                    m += y.monthCount
                }
            }
            var leap = false
            val leapMonth: Int = y.leapMonth
            if (leapMonth > 0) {
                if (m == leapMonth + 1) {
                    leap = true
                }
                if (m > leapMonth) {
                    m--
                }
            }
            ty = y.year
            tm = if (leap) -m else m
        }
        return object: AbstractLeapMonth(ty, tm) {}
    }
}

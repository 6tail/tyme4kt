package com.tyme.unit

/**
 * 抽象周
 *
 * @author 6tail
 */
abstract class AbstractWeek(year: Int, month: Int, index: Int, start: Int): WeekUnit(year, month, index, start) {
    /**
     * 抽象月
     */
    open val abstractMonth: AbstractMonth
        get() = throw UnsupportedOperationException()

    override fun next(n: Int): AbstractWeek {
        var d = index + n
        var m: AbstractMonth = abstractMonth
        if (n > 0) {
            var weekCount = m.getWeekCount(start)
            while (d >= weekCount) {
                d -= weekCount
                m = m.next(1)
                if (m.firstDay.getWeek().index != start) {
                    d += 1
                }
                weekCount = m.getWeekCount(start)
            }
        } else if (n < 0) {
            while (d < 0) {
                if (m.firstDay.getWeek().index != start) {
                    d -= 1
                }
                m = m.next(-1)
                d += m.getWeekCount(start)
            }
        }
        return object: AbstractWeek(m.year, m.monthValue, d, start) {}
    }

    override fun toString(): String {
        return abstractMonth.toString() + getName()
    }
}

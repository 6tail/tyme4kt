package com.tyme.solar

import com.tyme.unit.WeekUnit
import kotlin.jvm.JvmStatic

/**
 * 公历周
 *
 * @author 6tail
 */
class SolarWeek(
    year: Int,
    month: Int,
    index: Int,
    start: Int
) : WeekUnit(year, month, index, start) {
    init {
        validate(year, month, index, start)
    }

    /**
     * 公历月
     *
     * @return 公历月
     */
    fun getSolarMonth(): SolarMonth {
        return SolarMonth.fromYm(year, month)
    }

    /**
     * 位于当年的索引
     *
     * @return 索引
     */
    fun getIndexInYear(): Int {
        var i = 0
        val firstDay: SolarDay = getFirstDay()
        // 今年第1周
        var w = SolarWeek(year, 1, 0, start)
        while (w.getFirstDay() != firstDay) {
            w = w.next(1)
            i++
        }
        return i
    }

    override fun getName(): String {
        return NAMES[index]
    }

    override fun toString(): String {
        return getSolarMonth().toString() + getName()
    }

    override fun next(n: Int): SolarWeek {
        var d: Int = index + n
        var m: SolarMonth = getSolarMonth()
        if (n > 0) {
            var weekCount: Int = m.getWeekCount(start)
            while (d >= weekCount) {
                d -= weekCount
                m = m.next(1)
                if (m.getFirstDay().getWeek().getIndex() != start) {
                    d += 1
                }
                weekCount = m.getWeekCount(start)
            }
        } else if (n < 0) {
            while (d < 0) {
                if (m.getFirstDay().getWeek().getIndex() != start) {
                    d -= 1
                }
                m = m.next(-1)
                d += m.getWeekCount(start)
            }
        }
        return SolarWeek(m.year, m.month, d, start)
    }

    /**
     * 本周第1天
     *
     * @return 公历日
     */
    fun getFirstDay(): SolarDay {
        val firstDay = SolarDay(year, month, 1)
        return firstDay.next(index * 7 - indexOf(firstDay.getWeek().getIndex() - start, 7))
    }

    /**
     * 本周公历日列表
     *
     * @return 公历日列表
     */
    fun getDays(): List<SolarDay> {
        val l: MutableList<SolarDay> = ArrayList(7)
        val d: SolarDay = getFirstDay()
        l.add(d)
        for (i in 1..6) {
            l.add(d.next(i))
        }
        return l
    }

    override fun equals(other: Any?): Boolean {
        return other is SolarWeek && other.getFirstDay() == getFirstDay()
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    companion object {
        val NAMES: Array<String> = arrayOf("第一周", "第二周", "第三周", "第四周", "第五周", "第六周")

        @JvmStatic
        fun validate(year: Int, month: Int, index: Int, start: Int) {
            validate(index, start)
            val m = SolarMonth(year, month)
            if (index >= m.getWeekCount(start)) {
                throw IllegalArgumentException("illegal solar week index: $index in month: $m")
            }
        }

        @JvmStatic
        fun fromYm(year: Int, month: Int, index: Int, start: Int): SolarWeek {
            return SolarWeek(year, month, index, start)
        }
    }
}

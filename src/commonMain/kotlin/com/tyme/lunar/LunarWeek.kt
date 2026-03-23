package com.tyme.lunar

import com.tyme.unit.WeekUnit
import kotlin.jvm.JvmStatic

/**
 * 农历周
 *
 * @author 6tail
 */
class LunarWeek(
    year: Int,
    month: Int,
    index: Int,
    start: Int
) : WeekUnit(year, month, index, start) {
    init {
        validate(year, month, index, start)
    }

    /**
     * 农历月
     *
     * @return 农历月
     */
    fun getLunarMonth(): LunarMonth{
        return LunarMonth(year, month)
    }

    override fun getName(): String {
        return NAMES[index]
    }

    override fun toString(): String {
        return "${getLunarMonth()}${getName()}"
    }

    override fun next(n: Int): LunarWeek {
        var d: Int = index + n
        var m: LunarMonth = getLunarMonth()
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
        } else {
            while (d < 0) {
                if (m.getFirstDay().getWeek().getIndex() != start) {
                    d -= 1
                }
                m = m.next(-1)
                d += m.getWeekCount(start)
            }
        }
        return LunarWeek(m.year, m.getMonthWithLeap(), d, start)
    }

    /**
     * 本周第1天
     *
     * @return 农历日
     */
    fun getFirstDay(): LunarDay {
        val firstDay = LunarDay(year, month, 1)
        return firstDay.next(index * 7 - indexOf(firstDay.getWeek().getIndex() - start, 7))
    }

    /**
     * 本周农历日列表
     *
     * @return 农历日列表
     */
    fun getDays(): MutableList<LunarDay> {
        val l: MutableList<LunarDay> = ArrayList(7)
        val d: LunarDay = getFirstDay()
        l.add(d)
        for (i in 1..6) {
            l.add(d.next(i))
        }
        return l
    }

    override fun equals(other: Any?): Boolean {
        return other is LunarWeek && toString() == other.toString()
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    companion object {
        val NAMES: Array<String> = arrayOf("第一周", "第二周", "第三周", "第四周", "第五周", "第六周")

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

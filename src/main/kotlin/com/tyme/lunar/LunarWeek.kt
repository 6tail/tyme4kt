package com.tyme.lunar

import com.tyme.AbstractTyme
import com.tyme.culture.Week
import kotlin.jvm.JvmStatic

/**
 * 农历周
 *
 * @author 6tail
 */
class LunarWeek(
    year: Int,
    month: Int,
    /** 索引，0-5 */
    private var index: Int,
    start: Int
) : AbstractTyme() {
    /** 月 */
    private var month: LunarMonth
    /** 起始星期 */
    private var start: Week

    init {
        require(index in 0..5) { "illegal lunar week index: $index" }
        require(start in 0..6) { "illegal lunar week start: $start" }
        val m: LunarMonth = LunarMonth.fromYm(year, month)
        require(index < m.getWeekCount(start)) { "illegal lunar week index: $index in month: $m" }
        this.month = m
        this.start = Week(start)
    }

    /**
     * 农历月
     *
     * @return 农历月
     */
    fun getLunarMonth(): LunarMonth{
        return month
    }

    /**
     * 年
     *
     * @return 年
     */
    fun getYear(): Int {
        return month.getYear()
    }

    /**
     * 月
     *
     * @return 月
     */
    fun getMonth(): Int {
        return month.getMonthWithLeap()
    }

    /**
     * 索引
     *
     * @return 索引，0-5
     */
    fun getIndex(): Int {
        return index
    }

    /**
     * 起始星期
     *
     * @return 星期
     */
    fun getStart(): Week {
        return start
    }

    override fun getName(): String {
        return NAMES[index]
    }

    override fun toString(): String {
        return "${month}${getName()}"
    }

    override fun next(n: Int): LunarWeek {
        val startIndex: Int = start.getIndex()
        if (n == 0) {
            return LunarWeek(getYear(), getMonth(), index, startIndex)
        }
        var d: Int = index + n
        var m: LunarMonth = month
        if (n > 0) {
            var weekCount: Int = m.getWeekCount(startIndex)
            while (d >= weekCount) {
                d -= weekCount
                m = m.next(1)
                if (LunarDay(m.getYear(), m.getMonthWithLeap(), 1).getWeek() != start) {
                    d += 1
                }
                weekCount = m.getWeekCount(startIndex)
            }
        } else {
            while (d < 0) {
                if (LunarDay(m.getYear(), m.getMonthWithLeap(), 1).getWeek() != start) {
                    d -= 1
                }
                m = m.next(-1)
                d += m.getWeekCount(startIndex)
            }
        }
        return LunarWeek(m.getYear(), m.getMonthWithLeap(), d, startIndex)
    }

    /**
     * 本周第1天
     *
     * @return 农历日
     */
    fun getFirstDay(): LunarDay {
        val firstDay = LunarDay(getYear(), getMonth(), 1)
        return firstDay.next(index * 7 - indexOf(firstDay.getWeek().getIndex() - start.getIndex(), 7))
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
        fun fromYm(year: Int, month: Int, index: Int, start: Int): LunarWeek {
            return LunarWeek(year, month, index, start)
        }
    }
}

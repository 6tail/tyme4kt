package com.tyme.solar

import com.tyme.AbstractTyme
import com.tyme.culture.Week

/**
 * 公历周
 *
 * @author 6tail
 */
class SolarWeek(
    /** 年 */
    year: Int,
    /** 月 */
    month: Int,
    /** 索引，0-5 */
    private var index: Int,
    /** 起始星期，1234560分别代表星期一至星期天 */
    start: Int
) : AbstractTyme() {

    /** 月 */
    private var month: SolarMonth

    /** 起始星期 */
    private var start: Week

    init {
        require(index in 0..5) { "illegal solar week index: $index" }
        require(start in 0..6) { "illegal solar week start: $start" }
        val m = SolarMonth(year, month)
        require(index < m.getWeekCount(start)) { "illegal solar week index: $index in month: $m" }
        this.month = m
        this.start = Week(start)
    }

    /**
     * 公历月
     *
     * @return 公历月
     */
    fun getSolarMonth(): SolarMonth {
        return month
    }

    /**
     * 年
     *
     * @return 年
     */
    fun getYear(): Int {
        return  month.getYear()
    }

    /**
     * 月
     *
     * @return 月
     */
    fun getMonth(): Int {
        return month.getMonth()
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
     * 位于当年的索引
     *
     * @return 索引
     */
    fun getIndexInYear(): Int {
        var i = 0
        val firstDay: SolarDay = getFirstDay()
        // 今年第1周
        var w = SolarWeek(getYear(), 1, 0, start.getIndex())
        while (w.getFirstDay() != firstDay) {
            w = w.next(1)
            i++
        }
        return i
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
        return month.toString() + getName()
    }

    override fun next(n: Int): SolarWeek {
        val startIndex: Int = start.getIndex()
        var d = index
        var m: SolarMonth = month
        if (n > 0) {
            d += n
            var weekCount: Int = m.getWeekCount(startIndex)
            while (d >= weekCount) {
                d -= weekCount
                m = m.next(1)
                if (SolarDay(m.getYear(), m.getMonth(), 1).getWeek() != start) {
                    d += 1
                }
                weekCount = m.getWeekCount(startIndex)
            }
        } else if (n < 0) {
            d += n
            while (d < 0) {
                if (SolarDay(m.getYear(), m.getMonth(), 1).getWeek() != start) {
                    d -= 1
                }
                m = m.next(-1)
                d += m.getWeekCount(startIndex)
            }
        }
        return SolarWeek(m.getYear(), m.getMonth(), d, startIndex)
    }

    /**
     * 本周第1天
     *
     * @return 公历日
     */
    fun getFirstDay(): SolarDay {
        val firstDay = SolarDay(getYear(), getMonth(), 1)
        return firstDay.next(index * 7 - indexOf(firstDay.getWeek().getIndex() - start.getIndex(), 7))
    }

    /**
     * 本周公历日列表
     *
     * @return 公历日列表
     */
    fun getDays(): List<SolarDay> {
        val l: MutableList<SolarDay> = ArrayList(7)
        val d = getFirstDay()
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
        fun fromYm(year: Int, month: Int, index: Int, start: Int): SolarWeek {
            return SolarWeek(year, month, index, start)
        }
    }
}

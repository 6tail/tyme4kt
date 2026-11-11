package com.tyme.solar

import com.tyme.unit.AbstractMonth
import com.tyme.unit.AbstractWeek
import kotlin.jvm.JvmStatic

/**
 * 公历周
 *
 * @author 6tail
 */
class SolarWeek(
    year: Int,
    month: Int,
    /**
     * 索引，0-5
     */
    index: Int,
    /**
     * 起始星期，1234560分别代表星期一至星期天
     */
    start: Int
): AbstractWeek(year, month, index, start) {

    init {
        validate(year, month, index, start)
    }

    /**
     * 公历月
     */
    val solarMonth: SolarMonth
        get() = SolarMonth(year, month)

    override val abstractMonth: AbstractMonth
        get() = solarMonth

    /**
     * 位于当年的索引
     */
    val indexInYear: Int
        get() {
            var i = 0
            // 今年第1周
            var w = SolarWeek(year, 1, 0, start)
            while (w.firstDay != firstDay) {
                w = w.next(1)
                i++
            }
            return i
        }

    override fun getName(): String {
        return NAMES[index]
    }

    override fun next(n: Int): SolarWeek {
        val w = super.next(n)
        return SolarWeek(w.year, w.month, w.index, start)
    }

    /**
     * 本周第1天
     */
    val firstDay: SolarDay
        get() {
            val firstDay = SolarDay(year, month, 1)
            return firstDay.next(index * 7 - indexOf(firstDay.getWeek().index - start, 7))
        }

    /**
     * 本周公历日列表
     *
     * @return 公历日列表
     */
    fun getDays(): List<SolarDay> {
        val d: SolarDay = firstDay
        return List(7) { i ->
            d.next(i)
        }
    }

    override fun equals(other: Any?): Boolean {
        return other is SolarWeek && firstDay == other.firstDay
    }

    override fun hashCode(): Int {
        return firstDay.hashCode()
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

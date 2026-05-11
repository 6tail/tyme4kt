package com.tyme.festival

import com.tyme.event.Event
import com.tyme.solar.SolarDay
import kotlin.jvm.JvmStatic

/**
 * 公历现代节日
 *
 * @author 6tail
 */
class SolarFestival(
    /**
     * 索引
     */
    private var index: Int,
    /**
     * 事件
     */
    private var event: Event,
    /**
     * 公历日
     */
    private var day: SolarDay
) : AbstractFestival(index, event, day) {

    /**
     * 公历日
     *
     * @return 公历日
     */
    override fun getDay(): SolarDay {
        return day
    }

    /**
     * 起始年
     *
     * @return 年
     */
    fun getStartYear(): Int {
        return event.getStartYear()
    }

    override fun next(n: Int): SolarFestival? {
        val size = NAMES.size
        val i = index + n
        return fromIndex((day.year * size + i) / size, indexOf(i, size))
    }

    override fun equals(other: Any?): Boolean {
        return other is SolarFestival && toString() == other.toString()
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    companion object {
        val NAMES: Array<String> = arrayOf("元旦", "妇女节", "植树节", "劳动节", "青年节", "儿童节", "建党节", "建军节", "教师节", "国庆节")
        var DATA: String = "0VV__0Ux0Xc__0Ux0Xg__0_Q0ZV__0Ux0ZY__0Ux0aV__0Ux0bV__0Uo0cV__0Ug0de__0_V0eV__0Ux"

        @JvmStatic
        fun fromIndex(year: Int, index: Int): SolarFestival? {
            if (index < 0 || index >= NAMES.size) {
                return null
            }
            val start: Int = index * 8
            val e = Event(NAMES[index], "@${DATA.substring(start, start + 8)}")
            return if (year < e.getStartYear()) null else SolarFestival(index, e, SolarDay.fromYmd(year, e.getValue(2), e.getValue(3)))
        }

        @JvmStatic
        fun fromYmd(year: Int, month: Int, day: Int): SolarFestival? {
            val d: SolarDay = SolarDay.fromYmd(year, month, day)
            var i = 0
            val j: Int = NAMES.size
            while (i < j) {
                val start: Int = i * 8
                val e = Event(NAMES[i], "@${DATA.substring(start, start + 8)}")
                if (d.year >= e.getStartYear() && d.month == e.getValue(2) && d.day == e.getValue(3)) {
                    return SolarFestival(i, e, d)
                }
                i++
            }
            return null
        }
    }
}

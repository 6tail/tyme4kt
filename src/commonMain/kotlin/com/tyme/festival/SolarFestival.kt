package com.tyme.festival

import com.tyme.event.Event
import com.tyme.solar.SolarDay
import kotlin.jvm.JvmStatic

/**
 * 公历现代节日
 *
 * @author 6tail
 */
class SolarFestival(index: Int, event: Event, private val day: SolarDay): AbstractFestival(index, event, day) {

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
        return fromIndex((getDay().year * size + i) / size, indexOf(i, size))
    }

    companion object {
        val NAMES: Array<String> = arrayOf("元旦", "妇女节", "植树节", "劳动节", "青年节", "儿童节", "建党节", "建军节", "教师节", "国庆节")
        const val DATA: String = "0VV__0Ux0Xc__0Ux0Xg__0_Q0ZV__0Ux0ZY__0Ux0aV__0Ux0bV__0Uo0cV__0Ug0de__0_V0eV__0Ux"

        @JvmStatic
        fun fromIndex(year: Int, index: Int): SolarFestival? {
            val e = buildEvent(NAMES, DATA, index)
            if (null == e || year < e.getStartYear()) {
                return null
            }
            return SolarFestival(index, e, SolarDay(year, e.getValue(2), e.getValue(3)))
        }

        @JvmStatic
        fun fromYmd(year: Int, month: Int, day: Int): SolarFestival? {
            val d: SolarDay = SolarDay(year, month, day)
            for (i in NAMES.indices) {
                val e = buildEvent(NAMES, DATA, i) ?: continue
                if (d.year >= e.getStartYear() && d.month == e.getValue(2) && d.day == e.getValue(3)) {
                    return SolarFestival(i, e, d)
                }
            }
            return null
        }
    }
}

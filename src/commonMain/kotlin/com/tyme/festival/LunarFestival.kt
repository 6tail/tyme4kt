package com.tyme.festival

import com.tyme.enums.EventType
import com.tyme.event.Event
import com.tyme.lunar.LunarDay
import com.tyme.solar.SolarTerm
import com.tyme.solar.SolarTermDay
import com.tyme.unit.MonthUnit
import kotlin.jvm.JvmStatic

/**
 * 农历传统节日（依据国家标准《农历的编算和颁行》GB/T 33661-2017）
 *
 * @author 6tail
 */
class LunarFestival(index: Int, event: Event, private val day: LunarDay): AbstractFestival(index, event, day) {
    /**
     * 农历日
     *
     * @return 农历日
     */
    override fun getDay(): LunarDay {
        return day
    }

    /**
     * 节气，非节气返回null
     *
     * @return 节气
     */
    fun getSolarTerm(): SolarTerm? {
        val t = getDay().getSolarDay().getTermDay()
        return if (t.dayIndex == 0) t.getSolarTerm() else null
    }

    override fun next(n: Int): LunarFestival? {
        val size = NAMES.size
        val i = index + n
        return fromIndex((getDay().year * size + i) / size, indexOf(i, size))
    }

    companion object {
        val NAMES: Array<String> = arrayOf("春节", "元宵节", "龙头节", "上巳节", "清明节", "端午节", "七夕节", "中元节", "中秋节", "重阳节", "冬至节", "腊八节", "除夕")
        var DATA: String = "2VV__0002Vj__0002WW__0002XX__0003b___0002ZZ__0002bb__0002bj__0002cj__0002dd__0003s___0002gc__0002hV_U000"

        @JvmStatic
        fun fromIndex(year: Int, index: Int): LunarFestival? {
            val e: Event? = buildEvent(NAMES, DATA, index)
            if (null != e) {
                when (e.getType()) {
                    EventType.LUNAR_DAY -> {
                        val m = e.getMonth(year)
                        val d = LunarDay(m.year, m.month, e.getValue(3))
                        val offset = e.getValue(5)
                        return LunarFestival(index, e, if (0 == offset) d else d.next(offset))
                    }

                    EventType.TERM_DAY -> {
                        return LunarFestival(index, e, SolarTerm(year, e.getValue(2)).getSolarDay().getLunarDay())
                    }

                    else -> {}
                }
            }
            return null
        }

        @JvmStatic
        fun fromYmd(year: Int, month: Int, day: Int): LunarFestival? {
            val d: LunarDay = LunarDay(year, month, day)
            var i = 0
            val j: Int = NAMES.size
            while (i < j) {
                val start: Int = i * 8
                val e = Event(NAMES[i], "@${DATA.substring(start, start + 8)}")
                when (e.getType()) {
                    EventType.LUNAR_DAY -> {
                        val offset: Int = e.getValue(5)
                        if (0 == offset) {
                            if (d.month == e.getValue(2) && d.day == e.getValue(3)) {
                                return LunarFestival(i, e, d)
                            }
                        } else {
                            val m: MonthUnit = e.getMonth(d.year)
                            val next: LunarDay = d.next(-offset)
                            if (next.year == m.year && next.month == m.month && next.day == e.getValue(3)) {
                                return LunarFestival(i, e, d)
                            }
                        }
                    }

                    EventType.TERM_DAY -> {
                        val term: SolarTermDay = d.getSolarDay().getTermDay()
                        if (term.dayIndex == 0 && term.getSolarTerm().index == e.getValue(2) % 24) {
                            return LunarFestival(i, e, d)
                        }
                    }
                    else -> return null
                }
                i++
            }
            return null
        }
    }
}

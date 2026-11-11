package com.tyme.event

import com.tyme.AbstractCulture
import com.tyme.enums.EventType
import com.tyme.lunar.LunarDay
import com.tyme.lunar.LunarMonth
import com.tyme.solar.SolarDay
import com.tyme.solar.SolarMonth
import com.tyme.solar.SolarTerm
import com.tyme.unit.MonthUnit
import kotlin.jvm.JvmStatic

/**
 * 事件
 *
 * @author 6tail
 */
class Event(
    /**
     * 名称
     */
    private val name: String,
    /**
     * 数据
     */
    val data: String
): AbstractCulture() {
    init {
        validate(data)
    }

    private fun getCharIndex(index: Int): Int {
        return EventManager.CHARS.indexOf(data[index])
    }

    fun getValue(index: Int): Int {
        return getCharIndex(index) - 31
    }

    fun getMonth(year: Int): MonthUnit {
        var y: Int = year
        var m: Int = getValue(2)
        if (m > 12) {
            m = 1
            y += 1
        }
        return MonthUnit(y, m)
    }

    /**
     * 事件类型
     *
     * @return 事件类型
     */
    fun getType(): EventType? {
        return EventType.fromCode(getCharIndex(1))
    }

    /**
     * 名称
     *
     * @return 名称
     */
    override fun getName(): String {
        return name
    }

    /**
     * 起始年
     *
     * @return 年
     */
    fun getStartYear(): Int {
        var n = 0
        val size: Int = EventManager.CHARS.length
        for (i in 0..2) {
            n = n * size + getCharIndex(6 + i)
        }
        return n
    }

    /**
     * 公历日
     *
     * @param year 年
     * @return 公历日，如果当年没有该事件，返回null
     */
    fun getSolarDay(year: Int): SolarDay? {
        val type: EventType = getType() ?: return null
        if (year < getStartYear()) {
            return null
        }
        val d: SolarDay? = when (type) {
            EventType.SOLAR_DAY -> getSolarDayByDay(year)
            EventType.SOLAR_WEEK -> getSolarDayByWeek(year)
            EventType.LUNAR_DAY -> getSolarDayByLunarDay(year)
            EventType.TERM_DAY -> getSolarDayByTerm(year)
            EventType.TERM_HS -> getSolarDayByTermHeavenStem(year)
            EventType.TERM_EB -> getSolarDayByTermEarthBranch(year)
        }
        if (null == d) {
            return null
        }
        val offset: Int = getValue(5)
        return if (0 == offset) d else d.next(offset)
    }

    private fun getSolarDayByDay(year: Int, isLunar: Boolean = false): SolarDay? {
        val month: MonthUnit = getMonth(year)
        val y: Int = month.year
        val m: Int = month.month
        val d: Int = getValue(3)
        val delay: Int = getValue(4)
        val lastDay: Int = if (isLunar) LunarMonth(y, m).dayCount else SolarMonth.getDayCount(y, m)
        if (d > lastDay) {
            if (0 == delay) {
                return null
            } else if (delay < 0) {
                val day = d + delay
                if (day in 1..lastDay) {
                    return if (isLunar) LunarDay(y, m, day).getSolarDay() else SolarDay(y, m, day)
                }
                return null
            }
            return if (isLunar) LunarDay(y, m, lastDay).getSolarDay().next(delay) else SolarDay(y, m, lastDay).next(delay)
        }
        return if (isLunar) LunarDay(y, m, d).getSolarDay() else SolarDay(y, m, d)
    }

    private fun getSolarDayByLunarDay(year: Int): SolarDay? {
        return getSolarDayByDay(year, true)
    }

    private fun getSolarDayByWeek(year: Int): SolarDay? {
        // 第几个星期
        val n: Int = getValue(3)
        if (n == 0) {
            return null
        }
        val m = SolarMonth(year, getValue(2))
        // 星期几
        val w: Int = getValue(4)
        if (n > 0) {
            // 当月第1天
            val d: SolarDay = m.firstDay as SolarDay
            // 往后找第几个星期几
            return d.next(d.getWeek().stepsTo(w) + 7 * n - 7)
        }
        // 当月最后一天
        val d: SolarDay = SolarDay(year, m.month, m.dayCount)
        // 往前找第几个星期几
        return d.next(d.getWeek().stepsBackTo(w) + 7 * n + 7)
    }

    private fun getSolarDayByTerm(year: Int): SolarDay {
        val d: SolarDay = SolarTerm(year, getValue(2)).getSolarDay()
        val offset: Int = getValue(4)
        return if (0 == offset) d else d.next(offset)
    }

    private fun getSolarDayByTermHeavenStem(year: Int): SolarDay {
        val d: SolarDay = getSolarDayByTerm(year)
        return d.next(d.getLunarDay().getSixtyCycle().getHeavenStem().stepsTo(getValue(3)))
    }

    private fun getSolarDayByTermEarthBranch(year: Int): SolarDay {
        val d: SolarDay = getSolarDayByTerm(year)
        return d.next(d.getLunarDay().getSixtyCycle().getEarthBranch().stepsTo(getValue(3)))
    }

    companion object {
        @JvmStatic
        fun validate(data: String) {
            if (data.length != 9) {
                throw IllegalArgumentException("illegal event data: $data")
            }
        }

        /**
         * 构造器
         *
         * @return 构造器
         */
        @JvmStatic
        fun builder(): EventBuilder {
            return EventBuilder()
        }

        @JvmStatic
        fun fromName(name: String): Event? {
            return EventManager.REGEX.replace("%s", Regex.escape(name)).toRegex().find(EventManager.DATA)?.let { Event(name, it.groupValues[1]) }
        }

        /**
         * 指定公历日的事件列表
         *
         * @param d 公历日
         * @return 事件列表
         */
        @JvmStatic
        fun fromSolarDay(d: SolarDay): List<Event?> {
            return all().filter { it.getSolarDay(d.year) == d }
        }

        /**
         * 所有事件
         *
         * @return 事件列表
         */
        @JvmStatic
        fun all(): List<Event> {
            return EventManager.REGEX.replace("%s", "[^@]+").toRegex().findAll(EventManager.DATA).map {
                Event(it.groupValues[2], it.groupValues[1])
            }.toList()
        }
    }
}
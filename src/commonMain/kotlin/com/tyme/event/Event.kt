package com.tyme.event

import com.tyme.AbstractCulture
import com.tyme.enums.EventType
import com.tyme.lunar.LunarDay
import com.tyme.lunar.LunarMonth
import com.tyme.solar.SolarDay
import com.tyme.solar.SolarMonth
import com.tyme.solar.SolarTerm
import kotlin.jvm.JvmStatic

/**
 * 事件
 *
 * @author 6tail
 */
class Event(
    /** 名称 */
    private var name: String,
    /** 数据 */
    private var data: String
) : AbstractCulture() {
    init {
        validate(data)
    }

    /**
     * 事件类型
     *
     * @return 事件类型
     */
    fun getType(): EventType? {
        return EventType.fromCode(EventManager.CHARS.indexOf(data[1]))
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
     * 数据
     *
     * @return 数据
     */
    fun getData(): String {
        return data
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
            n = n * size + EventManager.CHARS.indexOf(data[6 + i])
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
            EventType.SOLAR_DAY -> getSolarDayBySolarDay(year)
            EventType.SOLAR_WEEK -> getSolarDayByWeek(year)
            EventType.LUNAR_DAY -> getSolarDayByLunarDay(year)
            EventType.TERM_DAY -> getSolarDayByTerm(year)
            EventType.TERM_HS -> getSolarDayByTermHeavenStem(year)
            EventType.TERM_EB -> getSolarDayByTermEarthBranch(year)
        }
        if (null == d) {
            return null
        }
        val offset: Int = EventManager.CHARS.indexOf(data[5]) - 31
        return if (0 == offset) d else d.next(offset)
    }

    private fun getSolarDayBySolarDay(year: Int): SolarDay? {
        var y: Int = year
        var m: Int = EventManager.CHARS.indexOf(data[2]) - 31
        if (m > 12) {
            m = 1
            y += 1
        }
        val d: Int = EventManager.CHARS.indexOf(data[3]) - 31
        val delay: Int = EventManager.CHARS.indexOf(data[4]) - 31
        val month = SolarMonth(y, m)
        val lastDay: Int = month.getDayCount()
        if (d > lastDay) {
            if (0 == delay) {
                return null
            }
            return if (delay < 0) SolarDay.fromYmd(y, m, d + delay) else SolarDay.fromYmd(y, m, lastDay).next(delay)
        }
        return SolarDay.fromYmd(y, m, d)
    }

    private fun getSolarDayByLunarDay(year: Int): SolarDay? {
        var y: Int = year
        var m: Int = EventManager.CHARS.indexOf(data[2]) - 31
        if (m > 12) {
            m = 1
            y += 1
        }
        val d: Int = EventManager.CHARS.indexOf(data[3]) - 31
        val delay: Int = EventManager.CHARS.indexOf(data[4]) - 31
        val month = LunarMonth(y, m)
        val lastDay: Int = month.getDayCount()
        if (d > lastDay) {
            if (0 == delay) {
                return null
            }
            return if (delay < 0) LunarDay.fromYmd(y, m, d + delay).getSolarDay() else LunarDay.fromYmd(y, m, lastDay).getSolarDay().next(delay)
        }
        return LunarDay.fromYmd(y, m, d).getSolarDay()
    }

    private fun getSolarDayByWeek(year: Int): SolarDay? {
        // 第几个星期
        val n: Int = EventManager.CHARS.indexOf(data[3]) - 31
        if (n == 0) {
            return null
        }
        val m = SolarMonth(year, EventManager.CHARS.indexOf(data[2]) - 31)
        // 星期几
        val w: Int = EventManager.CHARS.indexOf(data[4]) - 31
        if (n > 0) {
            // 当月第1天
            val d: SolarDay = m.getFirstDay()
            // 往后找第几个星期几
            return d.next(d.getWeek().stepsTo(w) + 7 * n - 7)
        }
        // 当月最后一天
        val d: SolarDay = SolarDay.fromYmd(year, m.month, m.getDayCount())
        // 往前找第几个星期几
        return d.next(d.getWeek().stepsBackTo(w) + 7 * n + 7)
    }

    private fun getSolarDayByTerm(year: Int): SolarDay {
        val offset: Int = EventManager.CHARS.indexOf(data[4]) - 31
        val d: SolarDay = SolarTerm.fromIndex(year, EventManager.CHARS.indexOf(data[2]) - 31).getSolarDay()
        return if (0 == offset) d else d.next(offset)
    }

    private fun getSolarDayByTermHeavenStem(year: Int): SolarDay {
        val d: SolarDay = getSolarDayByTerm(year)
        return d.next(d.getLunarDay().getSixtyCycle().getHeavenStem().stepsTo(EventManager.CHARS.indexOf(data[3]) - 31))
    }

    private fun getSolarDayByTermEarthBranch(year: Int): SolarDay {
        val d: SolarDay = getSolarDayByTerm(year)
        return d.next(d.getLunarDay().getSixtyCycle().getEarthBranch().stepsTo(EventManager.CHARS.indexOf(data[3]) - 31))
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
            return EventManager.REGEX.replace("%s", name).toRegex().find(EventManager.DATA)?.let { Event(name, it.groupValues[1]) }
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
            return EventManager.REGEX.replace("%s", ".[^@]+").toRegex().findAll(EventManager.DATA).map {
                Event(it.groupValues[2], it.groupValues[1])
            }.toList()
        }
    }
}
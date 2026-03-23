package com.tyme.event

import com.tyme.enums.EventType

/**
 * 事件构建器
 *
 * @author 6tail
 */
class EventBuilder {
    /**
     * 事件名称
     */
    private var name: String? = null

    /**
     * 事件数据
     */
    private var data: CharArray = "@_____000".toCharArray()

    /**
     * 事件名称
     *
     * @param name 名称
     * @return 事件构造器
     */
    fun name(name: String): EventBuilder {
        this.name = name
        return this
    }

    private fun content(type: EventType, a: Int, b: Int, c: Int): EventBuilder {
        data[1] = EventManager.CHARS[type.getCode()]
        data[2] = EventManager.CHARS[31 + a]
        data[3] = EventManager.CHARS[31 + b]
        data[4] = EventManager.CHARS[31 + c]
        return this
    }

    /**
     * 公历日
     *
     * @param solarMonth 公历月（1至12）
     * @param solarDay   公历日（1至31）
     * @param delayDays  顺延天数，例如生日在2月29，非闰年没有2月29，是+1天，还是-1天（最远支持-31至31天）
     * @return 事件构建器
     */
    fun solarDay(solarMonth: Int, solarDay: Int, delayDays: Int): EventBuilder {
        return content(EventType.SOLAR_DAY, solarMonth, solarDay, delayDays)
    }

    /**
     * 农历日
     *
     * @param lunarMonth 农历月（-12至-1，1至12，闰月为负）
     * @param lunarDay   农历日（1至30）
     * @param delayDays  顺延天数，例如生日在某月的三十，但下一年当月可能只有29天，是+1天，还是-1天（最远支持-31至31天）
     * @return 事件构建器
     */
    fun lunarDay(lunarMonth: Int, lunarDay: Int, delayDays: Int): EventBuilder {
        return content(EventType.LUNAR_DAY, lunarMonth, lunarDay, delayDays)
    }

    /**
     * 公历第几个星期几
     *
     * @param solarMonth 公历月（1至12）
     * @param weekIndex  第几个星期（1为第1个星期，-1为倒数第1个星期）
     * @param week       星期几（0至6，0代表星期天，1代表星期一）
     * @return 事件构建器
     */
    fun solarWeek(solarMonth: Int, weekIndex: Int, week: Int): EventBuilder {
        return content(EventType.SOLAR_WEEK, solarMonth, weekIndex, week)
    }

    /**
     * 节气
     *
     * @param termIndex 节气索引（0至23）
     * @param delayDays 顺延天数（最远支持-31至31天）
     * @return 事件构建器
     */
    fun termDay(termIndex: Int, delayDays: Int): EventBuilder {
        return content(EventType.TERM_DAY, termIndex, 0, delayDays)
    }

    /**
     * 节气天干
     *
     * @param termIndex       节气索引（0至23）
     * @param heavenStemIndex 天干索引（0至9）
     * @param delayDays       顺延天数（最远支持-31至31天）
     * @return 事件构建器
     */
    fun termHeavenStem(termIndex: Int, heavenStemIndex: Int, delayDays: Int): EventBuilder {
        return content(EventType.TERM_HS, termIndex, heavenStemIndex, delayDays)
    }

    /**
     * 节气地支
     *
     * @param termIndex        节气索引（0至23）
     * @param earthBranchIndex 地支索引（0至11）
     * @param delayDays        顺延天数（最远支持-31至31天）
     * @return 事件构建器
     */
    fun termEarthBranch(termIndex: Int, earthBranchIndex: Int, delayDays: Int): EventBuilder {
        return content(EventType.TERM_EB, termIndex, earthBranchIndex, delayDays)
    }

    /**
     * 起始年
     *
     * @param year 年
     * @return 事件构造器
     */
    fun startYear(year: Int): EventBuilder {
        val size: Int = EventManager.CHARS.length
        var n = year
        for (i in 0..2) {
            data[8 - i] = EventManager.CHARS[n % size]
            n /= size
        }
        return this
    }

    /**
     * 偏移天数
     *
     * @param days 天数（最远支持-31至31天）
     * @return 事件构造器
     */
    fun offset(days: Int): EventBuilder {
        data[5] = EventManager.CHARS[31 + days]
        return this
    }

    /**
     * 生成事件
     *
     * @return 事件
     */
    fun build(): Event {
        return Event(name ?: "", data.concatToString())
    }
}

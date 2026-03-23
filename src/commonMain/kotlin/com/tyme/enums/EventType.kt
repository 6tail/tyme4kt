package com.tyme.enums

import kotlin.jvm.JvmStatic

/**
 * 事件类型
 *
 * @author 6tail
 */
enum class EventType(private val code: Int) {
    SOLAR_DAY(0),
    SOLAR_WEEK(1),
    LUNAR_DAY(2),
    TERM_DAY(3),
    TERM_HS(4),
    TERM_EB(5);

    fun getName(): String {
        return when (this) {
            SOLAR_DAY -> "公历日期"
            SOLAR_WEEK -> "几月第几个星期几"
            LUNAR_DAY -> "农历日期"
            TERM_DAY -> "节气日期"
            TERM_HS -> "节气天干"
            TERM_EB -> "节气地支"
        }
    }

    fun getCode(): Int {
        return code
    }

    override fun toString(): String {
        return getName()
    }

    companion object {

        /**
         * 通过名称获取事件类型
         *
         * @param name 名称
         * @return 事件类型
         */
        @JvmStatic
        fun fromName(name: String): EventType? {
            return EventType.entries.find { it.getName() == name }
        }

        @JvmStatic
        fun fromCode(code: Int): EventType? {
            return EventType.entries.find { it.code == code }
        }
    }
}

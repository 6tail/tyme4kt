package com.tyme.enums

import kotlin.jvm.JvmStatic

/**
 * 节日类型
 *
 * @author 6tail
 */
enum class FestivalType(private val code: Int) {
    DAY(0),
    TERM(1),
    EVE(2);

    fun getName(): String {
        return when (this) {
            DAY -> "日期"
            TERM -> "节气"
            EVE -> "除夕"
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
         * 通过名称获取节日类型
         *
         * @param name 名称
         * @return 节日类型
         */
        @JvmStatic
        fun fromName(name: String): FestivalType? {
            for (entry in entries) {
                if (entry.getName() == name) {
                    return entry
                }
            }
            return null
        }

        @JvmStatic
        fun fromCode(code: Int): FestivalType? {
            for (entry in entries) {
                if (entry.code == code) {
                    return entry
                }
            }
            return null
        }
    }
}

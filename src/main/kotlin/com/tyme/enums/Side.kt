package com.tyme.enums

import kotlin.jvm.JvmStatic

/**
 * 内外
 *
 * @author 6tail
 */
enum class Side(private val code: Int) {
    IN(0),
    OUT(1);

    fun getName(): String {
        return when (this) {
            IN -> "内"
            OUT -> "外"
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
        fun fromName(name: String): Side? {
            for (entry in entries) {
                if (entry.getName() == name) {
                    return entry
                }
            }
            return null
        }

        @JvmStatic
        fun fromCode(code: Int): Side? {
            for (entry in entries) {
                if (entry.code == code) {
                    return entry
                }
            }
            return null
        }
    }
}

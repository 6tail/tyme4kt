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
         * 通过名称获取内外
         *
         * @param name 名称
         * @return 内外
         */
        @JvmStatic
        fun fromName(name: String): Side? {
            return Side.entries.find { it.getName() == name }
        }

        @JvmStatic
        fun fromCode(code: Int): Side? {
            return Side.entries.find { it.code == code }
        }
    }
}

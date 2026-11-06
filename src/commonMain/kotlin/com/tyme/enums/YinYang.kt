package com.tyme.enums

import kotlin.jvm.JvmStatic

/**
 * 阴阳
 *
 * @author 6tail
 */
enum class YinYang(val code: Int) {
    YIN(0),
    YANG(1);

    fun getName(): String {
        return when (this) {
            YIN -> "阴"
            YANG -> "阳"
        }
    }

    override fun toString(): String {
        return getName()
    }

    companion object {

        /**
         * 通过名称获取阴阳
         *
         * @param name 名称
         * @return 阴阳
         */
        @JvmStatic
        fun fromName(name: String): YinYang? {
            return entries.find { it.getName() == name }
        }

        @JvmStatic
        fun fromCode(code: Int): YinYang {
            return entries.first { it.code == code.and(1) }
        }
    }
}

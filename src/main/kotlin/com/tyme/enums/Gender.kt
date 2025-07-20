package com.tyme.enums

import kotlin.jvm.JvmStatic

/**
 * 性别
 *
 * @author 6tail
 */
enum class Gender(private val code: Int) {

    WOMAN(0),
    MAN(1);

    fun getName(): String {
        return when (this) {
            WOMAN -> "女"
            MAN -> "男"
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
        fun fromName(name: String): Gender? {
            for (entry in entries) {
                if (entry.getName() == name) {
                    return entry
                }
            }
            return null
        }

        @JvmStatic
        fun fromCode(code: Int): Gender? {
            for (entry in entries) {
                if (entry.code == code) {
                    return entry
                }
            }
            return null
        }
    }
}

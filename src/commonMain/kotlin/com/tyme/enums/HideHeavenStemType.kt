package com.tyme.enums

import kotlin.jvm.JvmStatic

/**
 * 藏干类型
 *
 * @author 6tail
 */
enum class HideHeavenStemType(private val code: Int) {
    RESIDUAL(0),
    MIDDLE(1),
    MAIN(2);

    fun getName(): String {
        return when (this) {
            RESIDUAL -> "余气"
            MIDDLE -> "中气"
            MAIN -> "本气"
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
         * 通过名称获取藏干类型
         *
         * @param name 名称
         * @return 藏干类型
         */
        @JvmStatic
        fun fromName(name: String): HideHeavenStemType? {
            return HideHeavenStemType.entries.find { it.getName() == name }
        }

        @JvmStatic
        fun fromCode(code: Int): HideHeavenStemType? {
            return HideHeavenStemType.entries.find { it.code == code }
        }
    }
}

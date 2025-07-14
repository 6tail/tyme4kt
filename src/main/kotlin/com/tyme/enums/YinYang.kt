package com.tyme.enums

/**
 * 阴阳
 *
 * @author 6tail
 */
enum class YinYang(private val code: Int) {
    YIN(0),
    YANG(1);

    fun getName(): String {
        return when (this) {
            YIN -> "阴"
            YANG -> "阳"
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
        fun fromName(name: String): YinYang? {
            for (entry in entries) {
                if (entry.getName() == name) {
                    return entry
                }
            }
            return null
        }

        @JvmStatic
        fun fromCode(code: Int): YinYang? {
            for (entry in entries) {
                if (entry.code == code) {
                    return entry
                }
            }
            return null
        }
    }
}

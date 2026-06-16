package com.tyme

import kotlin.jvm.JvmStatic

/**
 * 传统文化抽象
 *
 * @author 6tail
 */
abstract class AbstractCulture: Culture {
    abstract override fun getName(): String

    override fun toString(): String {
        return getName()
    }

    /**
     * 转换为不超范围的索引
     *
     * @param index 索引
     * @param size  数量
     * @return 索引，从0开始
     */
    protected fun indexOf(index: Int, size: Int): Int {
        return index.mod(size)
    }

    override fun hashCode(): Int {
        return toString().hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return other is Culture && toString() == other.toString()
    }

    companion object {
        @JvmStatic
        fun validateRange(value: Int, min: Int, max: Int, field: String) {
            if (value !in min..max) {
                throw IllegalArgumentException("illegal $field: $value")
            }
        }
    }
}

package com.tyme.unit

import com.tyme.AbstractTyme

/**
 * 年
 *
 * @author 6tail
 */
abstract class YearUnit(val year: Int): AbstractTyme() {

    /**
     * 用于比较大小的索引
     *
     * @return 索引
     */
    open fun getCompareIndex(): Long {
        return year * 10000L
    }

    /**
     * 是否在之前
     *
     * @param target 年
     * @return true/false
     */
    protected fun isBefore(target: YearUnit): Boolean {
        return getCompareIndex() < target.getCompareIndex()
    }

    /**
     * 是否在之后
     *
     * @param target 年
     * @return true/false
     */
    protected fun isAfter(target: YearUnit): Boolean {
        return getCompareIndex() > target.getCompareIndex()
    }
}

package com.tyme.unit

import com.tyme.AbstractTyme

/**
 * 年
 *
 * @author 6tail
 */
abstract class YearUnit(
    /** 年 */
    val year: Int
) : AbstractTyme() {

    /**
     * 用于比较大小的索引
     *
     * @return 索引
     */
    open fun getCompareIndex(): Long {
        return year * 10000L
    }
}

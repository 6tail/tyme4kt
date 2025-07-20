package com.tyme

/**
 * 带天索引的传统文化抽象
 *
 * @author 6tail
 */
abstract class AbstractCultureDay(
    /** 传统文化 */
    protected var culture: AbstractCulture,
    /** 天索引 */
    private var dayIndex: Int
) : AbstractCulture() {
    /**
     * 天索引
     *
     * @return 索引
     */
    fun getDayIndex(): Int {
        return dayIndex
    }

    protected fun getCulture(): Culture {
        return culture
    }

    override fun toString(): String {
        return "${culture}第${dayIndex + 1}天"
    }

    override fun getName(): String {
        return culture.getName()
    }
}

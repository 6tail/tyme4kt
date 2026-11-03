package com.tyme

/**
 * 带天索引的传统文化抽象
 *
 * @author 6tail
 */
abstract class AbstractCultureDay(
    /**
     * 传统文化
     */
    protected val culture: AbstractCulture,
    /**
     * 天索引
     */
    val dayIndex: Int
): AbstractCulture() {

    override fun toString(): String {
        return "${culture}第${dayIndex + 1}天"
    }

    override fun getName(): String {
        return culture.getName()
    }
}

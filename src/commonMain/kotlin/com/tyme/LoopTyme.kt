package com.tyme

/**
 * 可轮回的Tyme
 *
 * @author 6tail
 */
abstract class LoopTyme : AbstractTyme {
    /** 名称列表 */
    protected var names: Array<String>

    /** 索引，从0开始 */
    private var index: Int

    /**
     * 通过索引初始化
     *
     * @param names 名称列表
     * @param index 索引，支持负数，自动轮转
     */
    protected constructor(names: Array<String>, index: Int) {
        this.names = names
        this.index = indexOf(index)
    }

    /**
     * 通过名称初始化
     *
     * @param names 名称列表
     * @param name  名称
     */
    protected constructor(names: Array<String>, name: String) {
        this.names = names
        this.index = indexOf(name)
    }

    /**
     * 名称
     *
     * @return 名称
     */
    override fun getName(): String {
        return names[index]
    }

    /** 索引，从0开始 */
    fun getIndex(): Int{
        return index
    }

    /** 数量 */
    fun getSize(): Int{
        return names.size
    }

    /**
     * 名称对应的索引
     *
     * @param name 名称
     * @return 索引，从0开始
     */
    protected fun indexOf(name: String): Int {
        var i = 0
        val j = getSize()
        while (i < j) {
            if (names[i] == name) {
                return i
            }
            i++
        }
        throw IllegalArgumentException("illegal name: $name")
    }

    /**
     * 转换为不超范围的索引
     *
     * @param index 索引
     * @return 索引，从0开始
     */
    protected fun indexOf(index: Int): Int {
        return indexOf(index, getSize())
    }

    /**
     * 推移后的索引
     *
     * @param n 推移步数
     * @return 索引，从0开始
     */
    protected fun nextIndex(n: Int): Int {
        return indexOf(index + n)
    }

    /**
     * 到目标索引的步数
     *
     * @param targetIndex 目标索引
     * @return 步数
     */
    fun stepsTo(targetIndex: Int): Int {
        return indexOf(targetIndex - index)
    }
}

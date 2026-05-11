package com.tyme.culture

import com.tyme.LoopTyme
import kotlin.jvm.JvmStatic

/**
 * 五行
 *
 * @author 6tail
 */
open class Element: LoopTyme {
    constructor(names: Array<String>, index: Int): super(names, index)

    constructor(names: Array<String>, name: String): super(names, name)

    constructor(index: Int): this(NAMES, index)

    constructor(name: String): this(NAMES, name)

    override fun next(n: Int): Element {
        return Element(nextIndex(n))
    }

    /**
     * 我生者
     *
     * @return 五行
     */
    open fun getReinforce(): Element {
        return this.next(1)
    }

    /**
     * 我克者
     *
     * @return 五行
     */
    open fun getRestrain(): Element {
        return this.next(2)
    }

    /**
     * 生我者
     *
     * @return 五行
     */
    open fun getReinforced(): Element {
        return this.next(-1)
    }

    /**
     * 克我者
     *
     * @return 五行
     */
    open fun getRestrained(): Element {
        return this.next(-2)
    }

    /**
     * 方位
     *
     * @return 方位
     */
    fun getDirection(): Direction {
        return Direction(intArrayOf(2, 8, 4, 6, 0)[getIndex()])
    }

    companion object {
        val NAMES: Array<String> = arrayOf("木", "火", "土", "金", "水")

        @JvmStatic
        fun fromIndex(index: Int): Element {
            return Element(index)
        }

        @JvmStatic
        fun fromName(name: String): Element {
            return Element(name)
        }
    }
}

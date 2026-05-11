package com.tyme.rabbyung

import com.tyme.culture.Element
import kotlin.jvm.JvmStatic

/**
 * 藏历五行
 *
 * @author 6tail
 */
class RabByungElement: Element {
    constructor(index: Int) : super(NAMES, index)

    constructor(name: String) : super(NAMES, name)

    override fun next(n: Int): RabByungElement {
        return RabByungElement(nextIndex(n))
    }

    /**
     * 我生者
     *
     * @return 五行
     */
    override fun getReinforce(): RabByungElement {
        return next(1)
    }

    /**
     * 我克者
     *
     * @return 五行
     */
    override fun getRestrain(): RabByungElement {
        return next(2)
    }

    /**
     * 生我者
     *
     * @return 五行
     */
    override fun getReinforced(): RabByungElement {
        return next(-1)
    }

    /**
     * 克我者
     *
     * @return 五行
     */
    override fun getRestrained(): RabByungElement {
        return next(-2)
    }

    companion object {
        val NAMES: Array<String> = arrayOf("木", "火", "土", "铁", "水")

        @JvmStatic
        fun fromIndex(index: Int): RabByungElement {
            return RabByungElement(index)
        }

        @JvmStatic
        fun fromName(name: String): RabByungElement {
            return RabByungElement(name)
        }
    }
}
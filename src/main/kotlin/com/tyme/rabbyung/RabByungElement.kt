package com.tyme.rabbyung

import com.tyme.culture.Element
import kotlin.jvm.JvmStatic

/**
 * 藏历五行
 *
 * @author 6tail
 */
class RabByungElement: Element {
    constructor(index: Int) : super(index)

    constructor(name: String) : super(name.replace("铁", "金"))

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

    override fun getName(): String {
        return super.getName().replace("金", "铁")
    }

    companion object {
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
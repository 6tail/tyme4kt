package com.tyme.culture.star.nine

import com.tyme.LoopTyme
import com.tyme.culture.Direction
import com.tyme.culture.Element
import kotlin.jvm.JvmStatic

/**
 * 九星
 *
 * @author 6tail
 */
class NineStar: LoopTyme {
    constructor(index: Int): super(NAMES, index)

    constructor(name: String): super(NAMES, name)

    override fun next(n: Int): NineStar {
        return NineStar(nextIndex(n))
    }

    /**
     * 颜色
     *
     * @return 颜色
     */
    fun getColor(): String {
        return COLORS[index]
    }

    /**
     * 五行
     *
     * @return 五行
     */
    fun getElement(): Element {
        return getDirection().getElement()
    }

    /**
     * 北斗九星
     *
     * @return 北斗九星
     */
    fun getDipper(): Dipper {
        return Dipper(index)
    }

    /**
     * 方位
     *
     * @return 方位
     */
    fun getDirection(): Direction {
        return Direction(index)
    }

    override fun toString(): String {
        return getName() + getColor() + getElement().getName()
    }

    companion object {
        val NAMES: Array<String> = arrayOf("一", "二", "三", "四", "五", "六", "七", "八", "九")
        val COLORS: Array<String> = arrayOf("白", "黑", "碧", "绿", "黄", "白", "赤", "白", "紫")

        @JvmStatic
        fun fromIndex(index: Int): NineStar {
            return NineStar(index)
        }

        @JvmStatic
        fun fromName(name: String): NineStar {
            return NineStar(name)
        }
    }
}

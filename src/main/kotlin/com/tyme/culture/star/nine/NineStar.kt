package com.tyme.culture.star.nine

import com.tyme.LoopTyme
import com.tyme.culture.Direction
import com.tyme.culture.Element

/**
 * 九星
 *
 * @author 6tail
 */
class NineStar : LoopTyme {
    constructor(index: Int) : super(NAMES, index)

    constructor(name: String) : super(NAMES, name)

    override fun next(n: Int): NineStar {
        return NineStar(nextIndex(n))
    }

    /**
     * 颜色
     *
     * @return 颜色
     */
    fun getColor(): String {
        return arrayOf("白", "黑", "碧", "绿", "黄", "白", "赤", "白", "紫")[this.getIndex()]
    }

    /**
     * 五行
     *
     * @return 五行
     */
    fun getElement(): Element {
        return Element(intArrayOf(4, 2, 0, 0, 2, 3, 3, 2, 1)[this.getIndex()])
    }

    /**
     * 北斗九星
     *
     * @return 北斗九星
     */
    fun getDipper(): Dipper {
        return Dipper(this.getIndex())
    }

    /**
     * 方位
     *
     * @return 方位
     */
    fun getDirection(): Direction {
        return Direction(this.getIndex())
    }

    override fun toString(): String {
        return getName() + getColor() + getElement().getName()
    }

    companion object {
        val NAMES: Array<String> = arrayOf("一", "二", "三", "四", "五", "六", "七", "八", "九")

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

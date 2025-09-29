package com.tyme.culture.ren

import com.tyme.LoopTyme
import com.tyme.culture.Element
import com.tyme.culture.Luck
import kotlin.jvm.JvmStatic

/**
 * 小六壬
 *
 * @author 6tail
 */
class MinorRen : LoopTyme {
    constructor(index: Int) : super(NAMES, index)

    constructor(name: String) : super(NAMES, name)

    override fun next(n: Int): MinorRen {
        return MinorRen(nextIndex(n))
    }

    /**
     * 吉凶
     *
     * @return 吉凶
     */
    fun getLuck(): Luck {
        return Luck(getIndex() % 2)
    }

    /**
     * 五行
     *
     * @return 五行
     */
    fun getElement(): Element {
        return Element(intArrayOf(0, 4, 1, 3, 0, 2)[getIndex()])
    }

    companion object {
        val NAMES: Array<String> = arrayOf("大安", "留连", "速喜", "赤口", "小吉", "空亡")

        @JvmStatic
        fun fromIndex(index: Int): MinorRen {
            return MinorRen(index)
        }

        @JvmStatic
        fun fromName(name: String): MinorRen {
            return MinorRen(name)
        }
    }
}

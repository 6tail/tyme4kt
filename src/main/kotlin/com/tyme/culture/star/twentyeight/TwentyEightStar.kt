package com.tyme.culture.star.twentyeight

import com.tyme.LoopTyme
import com.tyme.culture.Animal
import com.tyme.culture.Land
import com.tyme.culture.Luck
import com.tyme.culture.Zone
import com.tyme.culture.star.seven.SevenStar

/**
 * 二十八宿
 *
 * @author 6tail
 */
class TwentyEightStar: LoopTyme {
    constructor(index: Int): super(NAMES, index)

    constructor(name: String): super(NAMES, name)

    override fun next(n: Int): TwentyEightStar {
        return TwentyEightStar(nextIndex(n))
    }

    /** 七曜 */
    fun getSevenStar(): SevenStar {
        return SevenStar(getIndex() % 7 + 4)
    }

    /** 九野 */
    fun getLand(): Land {
        return Land(intArrayOf(4, 4, 4, 2, 2, 2, 7, 7, 7, 0, 0, 0, 0, 5, 5, 5, 6, 6, 6, 1, 1, 1, 8, 8, 8, 3, 3, 3)[getIndex()])
    }

    /** 宫 */
    fun getZone(): Zone {
        return Zone(getIndex() / 7)
    }

    /** 动物 */
    fun getAnimal(): Animal {
        return Animal(getIndex())
    }

    /** 吉凶 */
    fun getLuck(): Luck {
        return Luck(intArrayOf(0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0)[getIndex()])
    }

    companion object {
        val NAMES: Array<String> = arrayOf("角", "亢", "氐", "房", "心", "尾", "箕", "斗", "牛", "女", "虚", "危", "室", "壁", "奎", "娄", "胃", "昴", "毕", "觜", "参", "井", "鬼", "柳", "星", "张", "翼", "轸")

        @JvmStatic
        fun fromIndex(index: Int): TwentyEightStar {
            return TwentyEightStar(index)
        }

        @JvmStatic
        fun fromName(name: String): TwentyEightStar {
            return TwentyEightStar(name)
        }
    }
}

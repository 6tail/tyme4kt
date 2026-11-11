package com.tyme.culture.star.twentyeight

import com.tyme.LoopTyme
import com.tyme.culture.*
import com.tyme.culture.star.seven.SevenStar
import com.tyme.sixtycycle.SixtyCycle
import kotlin.jvm.JvmStatic

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

    /**
     * 七曜
     *
     * @return 七曜
     * */
    fun getSevenStar(): SevenStar {
        return SevenStar(index % 7 + 4)
    }

    /**
     * 九野
     *
     * @return 九野
     * */
    fun getLand(): Land {
        return Land(intArrayOf(4, 4, 4, 2, 2, 2, 7, 7, 7, 0, 0, 0, 0, 5, 5, 5, 6, 6, 6, 1, 1, 1, 8, 8, 8, 3, 3, 3)[index])
    }

    /**
     * 宫
     *
     * @return 宫
     * */
    fun getZone(): Zone {
        return Zone(index / 7)
    }

    /**
     * 动物
     *
     * @return 动物
     * */
    fun getAnimal(): Animal {
        return Animal(index)
    }

    /**
     * 吉凶
     *
     * @return 吉凶
     * */
    fun getLuck(): Luck {
        return Luck(intArrayOf(0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0)[index])
    }

    companion object {
        val NAMES: Array<String> = arrayOf("角", "亢", "氐", "房", "心", "尾", "箕", "斗", "牛", "女", "虚", "危", "室", "壁", "奎", "娄", "胃", "昴", "毕", "觜", "参", "井", "鬼", "柳", "星", "张", "翼", "轸")

        @JvmStatic
        fun fromDay(day: SixtyCycle, week: Week): TwentyEightStar {
            return TwentyEightStar(10 + 8 * week.index - 7 * day.getEarthBranch().index)
        }

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

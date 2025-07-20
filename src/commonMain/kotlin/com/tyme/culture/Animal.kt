package com.tyme.culture

import com.tyme.LoopTyme
import com.tyme.culture.star.twentyeight.TwentyEightStar
import kotlin.jvm.JvmStatic

/**
 * 动物
 *
 * @author 6tail
 */
class Animal: LoopTyme {

    constructor(index: Int): super(NAMES, index)

    constructor(name: String): super(NAMES, name)

    override fun next(n: Int): Animal {
        return Animal(nextIndex(n))
    }

    /** 二十八宿 */
    fun getTwentyEightStar(): TwentyEightStar {
        return TwentyEightStar(this.getIndex())
    }

    companion object {
        val NAMES: Array<String> = arrayOf("蛟", "龙", "貉", "兔", "狐", "虎", "豹", "獬", "牛", "蝠", "鼠", "燕", "猪", "獝", "狼", "狗", "彘", "鸡", "乌", "猴", "猿", "犴", "羊", "獐", "马", "鹿", "蛇", "蚓")

        @JvmStatic
        fun fromIndex(index: Int): Animal {
            return Animal(index)
        }

        @JvmStatic
        fun fromName(name: String): Animal {
            return Animal(name)
        }
    }
}

package com.tyme.culture

import com.tyme.LoopTyme
import com.tyme.sixtycycle.EarthBranch
import kotlin.jvm.JvmStatic

/**
 * 生肖
 *
 * @author 6tail
 */
class Zodiac: LoopTyme {
    constructor(index: Int): super(NAMES, index)

    constructor(name: String): super(NAMES, name)

    override fun next(n: Int): Zodiac {
        return Zodiac(nextIndex(n))
    }

    /**
     * 地支
     *
     * @return 地支
     */
    fun getEarthBranch(): EarthBranch {
        return EarthBranch(this.getIndex())
    }

    override fun equals(other: Any?): Boolean {
        return other is Zodiac && other.toString() == this.toString()
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    companion object {
        val NAMES: Array<String> = arrayOf("鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪")

        @JvmStatic
        fun fromIndex(index: Int): Zodiac {
            return Zodiac(index)
        }

        @JvmStatic
        fun fromName(name: String): Zodiac {
            return Zodiac(name)
        }
    }
}

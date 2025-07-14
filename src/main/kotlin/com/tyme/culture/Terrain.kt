package com.tyme.culture

import com.tyme.LoopTyme

/**
 * 地势(长生十二神)
 *
 * @author 6tail
 */
class Terrain: LoopTyme {
    constructor(index: Int): super(NAMES, index)

    constructor(name: String): super(NAMES, name)

    override fun next(n: Int): Terrain {
        return Terrain(nextIndex(n))
    }

    companion object {
        val NAMES: Array<String> = arrayOf("长生", "沐浴", "冠带", "临官", "帝旺", "衰", "病", "死", "墓", "绝", "胎", "养")

        @JvmStatic
        fun fromIndex(index: Int): Terrain {
            return Terrain(index)
        }

        @JvmStatic
        fun fromName(name: String): Terrain {
            return Terrain(name)
        }
    }
}

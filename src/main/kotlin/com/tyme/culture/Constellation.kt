package com.tyme.culture

import com.tyme.LoopTyme

/**
 * 星座
 *
 * @author 6tail
 */
class Constellation: LoopTyme {
    constructor(index: Int): super(NAMES, index)

    constructor(name: String): super(NAMES, name)

    override fun next(n: Int): Constellation {
        return Constellation(nextIndex(n))
    }

    companion object {
        val NAMES: Array<String> = arrayOf("白羊", "金牛", "双子", "巨蟹", "狮子", "处女", "天秤", "天蝎", "射手", "摩羯", "水瓶", "双鱼")

        @JvmStatic
        fun fromIndex(index: Int): Constellation {
            return Constellation(index)
        }

        @JvmStatic
        fun fromName(name: String): Constellation {
            return Constellation(name)
        }
    }
}

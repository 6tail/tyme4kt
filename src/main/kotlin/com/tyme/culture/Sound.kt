package com.tyme.culture

import com.tyme.LoopTyme

/**
 * 纳音
 *
 * @author 6tail
 */
class Sound: LoopTyme {
    constructor(index: Int): super(NAMES, index)

    constructor(name: String): super(NAMES, name)

    override fun next(n: Int): Sound {
        return Sound(nextIndex(n))
    }

    companion object {
        val NAMES: Array<String> = arrayOf("海中金", "炉中火", "大林木", "路旁土", "剑锋金", "山头火", "涧下水", "城头土", "白蜡金", "杨柳木", "泉中水", "屋上土", "霹雳火", "松柏木", "长流水", "沙中金", "山下火", "平地木", "壁上土", "金箔金", "覆灯火", "天河水", "大驿土", "钗钏金", "桑柘木", "大溪水", "沙中土", "天上火", "石榴木", "大海水")

        @JvmStatic
        fun fromIndex(index: Int): Sound {
            return Sound(index)
        }

        @JvmStatic
        fun fromName(name: String): Sound {
            return Sound(name)
        }
    }
}

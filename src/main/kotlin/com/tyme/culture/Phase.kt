package com.tyme.culture

import com.tyme.LoopTyme

/**
 * 月相
 *
 * @author 6tail
 */
class Phase: LoopTyme {
    constructor(index: Int): super(NAMES, index)

    constructor(name: String): super(NAMES, name)

    override fun next(n: Int): Phase {
        return Phase(nextIndex(n))
    }

    companion object {
        val NAMES: Array<String> = arrayOf("朔月", "既朔月", "蛾眉新月", "蛾眉新月", "蛾眉月", "夕月", "上弦月", "上弦月", "九夜月", "宵月", "宵月", "宵月", "渐盈凸月", "小望月", "望月", "既望月", "立待月", "居待月", "寝待月", "更待月", "渐亏凸月", "下弦月", "下弦月", "有明月", "有明月", "蛾眉残月", "蛾眉残月", "残月", "晓月", "晦月")

        @JvmStatic
        fun fromIndex(index: Int): Phase {
            return Phase(index)
        }

        @JvmStatic
        fun fromName(name: String): Phase {
            return Phase(name)
        }
    }
}

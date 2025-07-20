package com.tyme.culture.star.nine

import com.tyme.LoopTyme
import kotlin.jvm.JvmStatic

/**
 * 北斗九星
 *
 * @author 6tail
 */
class Dipper : LoopTyme {
    constructor(index: Int) : super(NAMES, index)

    constructor(name: String) : super(NAMES, name)

    override fun next(n: Int): Dipper {
        return Dipper(nextIndex(n))
    }

    companion object {
        val NAMES: Array<String> = arrayOf("天枢", "天璇", "天玑", "天权", "玉衡", "开阳", "摇光", "洞明", "隐元")

        @JvmStatic
        fun fromIndex(index: Int): Dipper {
            return Dipper(index)
        }

        @JvmStatic
        fun fromName(name: String): Dipper {
            return Dipper(name)
        }
    }
}

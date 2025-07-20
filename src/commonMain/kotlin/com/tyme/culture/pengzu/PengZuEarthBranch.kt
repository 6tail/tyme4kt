package com.tyme.culture.pengzu

import com.tyme.LoopTyme
import kotlin.jvm.JvmStatic

/**
 * 地支彭祖百忌
 *
 * @author 6tail
 */
class PengZuEarthBranch: LoopTyme {
    constructor(name: String): super(NAMES, name)

    constructor(index: Int): super(NAMES, index)

    override fun next(n: Int): PengZuEarthBranch {
        return PengZuEarthBranch(nextIndex(n))
    }

    companion object {
        val NAMES: Array<String> = arrayOf("子不问卜自惹祸殃", "丑不冠带主不还乡", "寅不祭祀神鬼不尝", "卯不穿井水泉不香", "辰不哭泣必主重丧", "巳不远行财物伏藏", "午不苫盖屋主更张", "未不服药毒气入肠", "申不安床鬼祟入房", "酉不会客醉坐颠狂", "戌不吃犬作怪上床", "亥不嫁娶不利新郎")

        @JvmStatic
        fun fromIndex(index: Int): PengZuEarthBranch {
            return PengZuEarthBranch(index)
        }

        @JvmStatic
        fun fromName(name: String): PengZuEarthBranch {
            return PengZuEarthBranch(name)
        }
    }
}

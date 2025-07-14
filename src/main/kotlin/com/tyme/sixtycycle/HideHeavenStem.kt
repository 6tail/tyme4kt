package com.tyme.sixtycycle

import com.tyme.AbstractCulture
import com.tyme.enums.HideHeavenStemType

/**
 * 藏干（即人元，司令取天干，分野取天干的五行）
 *
 * @author 6tail
 */
class HideHeavenStem: AbstractCulture {
    /** 天干 */
    private var heavenStem: HeavenStem
    /** 藏干类型 */
    private var type: HideHeavenStemType

    constructor(heavenStem: HeavenStem, type: HideHeavenStemType): super(){
        this.heavenStem = heavenStem
        this.type = type
    }

    constructor(heavenStemName: String, type: HideHeavenStemType): super(){
        this.heavenStem = HeavenStem(heavenStemName)
        this.type = type
    }

    constructor(heavenStemIndex: Int, type: HideHeavenStemType): super(){
        this.heavenStem = HeavenStem(heavenStemIndex)
        this.type = type
    }

    override fun getName(): String {
        return heavenStem.getName()
    }

    fun getHeavenStem(): HeavenStem {
        return heavenStem
    }

    fun getType(): HideHeavenStemType {
        return type
    }
}

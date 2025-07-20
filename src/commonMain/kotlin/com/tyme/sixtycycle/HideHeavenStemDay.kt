package com.tyme.sixtycycle

import com.tyme.AbstractCultureDay

/**
 * 人元司令分野（地支藏干+天索引）
 *
 * @author 6tail
 */
class HideHeavenStemDay(hideHeavenStem: HideHeavenStem, dayIndex: Int): AbstractCultureDay(hideHeavenStem, dayIndex) {
    /**
     * 藏干
     *
     * @return 藏干
     */
    fun getHideHeavenStem(): HideHeavenStem {
        return culture as HideHeavenStem
    }

    override fun getName(): String {
        val heavenStem: HeavenStem = getHideHeavenStem().getHeavenStem()
        return heavenStem.getName() + heavenStem.getElement().getName()
    }

    override fun toString(): String {
        return "${getName()}第${getDayIndex() + 1}天"
    }
}

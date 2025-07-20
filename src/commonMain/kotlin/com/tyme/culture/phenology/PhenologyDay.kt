package com.tyme.culture.phenology

import com.tyme.AbstractCultureDay

/**
 * 七十二候
 *
 * @author 6tail
 */
class PhenologyDay(phenology: Phenology, dayIndex: Int): AbstractCultureDay(phenology, dayIndex) {

    /**
     * 候
     *
     * @return 候
     */
    fun getPhenology(): Phenology {
        return culture as Phenology
    }
}

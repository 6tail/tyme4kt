package com.tyme.culture

import com.tyme.AbstractCultureDay

/**
 * 月相第几天
 *
 * @author 6tail
 */
class PhaseDay(phase: Phase, dayIndex: Int): AbstractCultureDay(phase, dayIndex) {

    /**
     * 月相
     *
     * @return 月相
     */
    fun getPhase(): Phase {
        return culture as Phase
    }
}

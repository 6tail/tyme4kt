package com.tyme.culture.nine

import com.tyme.AbstractCultureDay

/**
 * 数九天
 *
 * @author 6tail
 */
class NineDay(nine: Nine, dayIndex: Int): AbstractCultureDay(nine, dayIndex) {

    /**
     * 数九
     *
     * @return 数九
     */
    fun getNine(): Nine {
        return culture as Nine
    }
}

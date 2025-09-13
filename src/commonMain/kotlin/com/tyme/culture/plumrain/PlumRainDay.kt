package com.tyme.culture.plumrain

import com.tyme.AbstractCultureDay

/**
 * 梅雨天
 *
 * @author 6tail
 */
class PlumRainDay(plumRain: PlumRain, dayIndex: Int) : AbstractCultureDay(plumRain, dayIndex) {

    /**
     * 梅雨
     *
     * @return 梅雨
     */
    fun getPlumRain(): PlumRain {
        return culture as PlumRain
    }

    override fun toString(): String {
        return if (this.getPlumRain().getIndex() == 0) super.toString() else culture.getName()
    }
}

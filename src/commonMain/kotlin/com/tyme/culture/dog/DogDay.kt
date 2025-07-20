package com.tyme.culture.dog

import com.tyme.AbstractCultureDay

/**
 * 三伏天
 *
 * @author 6tail
 */
class DogDay(dog: Dog, dayIndex: Int): AbstractCultureDay(dog, dayIndex) {

    /**
     * 三伏
     *
     * @return 三伏
     */
    fun getDog(): Dog {
        return culture as Dog
    }
}

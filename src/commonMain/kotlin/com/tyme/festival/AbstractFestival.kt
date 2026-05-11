package com.tyme.festival

import com.tyme.AbstractTyme
import com.tyme.event.Event
import com.tyme.unit.DayUnit

/**
 * 节日抽象
 *
 * @author 6tail
 */
abstract class AbstractFestival(
    /**
     * 索引
     */
    private var index: Int,
    /**
     * 事件
     */
    private var event: Event,
    /**
     * 日
     */
    private var day: DayUnit
) : AbstractTyme() {
    /**
     * 索引
     *
     * @return 索引
     */
    fun getIndex(): Int{
        return index
    }

    /**
     * 日
     *
     * @return 日
     */
    open fun getDay(): DayUnit {
        return day
    }

    override fun getName(): String {
        return event.getName()
    }

    override fun toString(): String {
        return "${getDay()} ${getName()}"
    }
}

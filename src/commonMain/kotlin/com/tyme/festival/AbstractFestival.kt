package com.tyme.festival

import com.tyme.AbstractTyme
import com.tyme.event.Event
import com.tyme.unit.DayUnit
import kotlin.jvm.JvmStatic

/**
 * 节日抽象
 *
 * @author 6tail
 */
abstract class AbstractFestival: AbstractTyme {
    /**
     * 索引
     */
    protected val index: Int

    /**
     * 日
     */
    private val day: DayUnit

    /**
     * 事件
     */
    protected val event: Event

    constructor(index: Int, event: Event, day: DayUnit): super() {
        this.index = index
        this.event = event
        this.day = day
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
        return "$day ${getName()}"
    }

    companion object {
        @JvmStatic
        protected fun buildEvent(names: Array<String>, data: String, index: Int): Event? {
            if (index < 0 || index >= names.size) {
                return null
            }
            val start = index * 8
            return Event(names[index], "@" + data.substring(start, start + 8))
        }
    }
}

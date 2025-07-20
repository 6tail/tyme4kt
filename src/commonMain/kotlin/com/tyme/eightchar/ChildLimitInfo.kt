package com.tyme.eightchar

import com.tyme.solar.SolarTime

/**
 * 童限信息
 *
 * @author 6tail
 */
class ChildLimitInfo(
    /** 开始(即出生)的公历时刻 */
    private var startTime: SolarTime,
    /** 结束(即开始起运)的公历时刻 */
    private var endTime: SolarTime,
    /** 年数  */
    private var yearCount: Int,
    /** 月数 */
    private var monthCount: Int,
    /** 日数 */
    private var dayCount: Int,
    /** 小时数 */
    private var hourCount: Int,
    /** 分钟数 */
    private var minuteCount: Int
) {
    fun getStartTime(): SolarTime {
        return startTime
    }

    fun getEndTime(): SolarTime {
        return endTime
    }

    fun getYearCount(): Int {
        return yearCount
    }

    fun getMonthCount(): Int {
        return monthCount
    }

    fun getDayCount(): Int {
        return dayCount
    }

    fun getHourCount(): Int {
        return hourCount
    }

    fun getMinuteCount(): Int {
        return minuteCount
    }
}

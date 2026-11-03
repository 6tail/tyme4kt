package com.tyme.eightchar

import com.tyme.solar.SolarTime

/**
 * 童限信息
 *
 * @param startTime 开始(即出生)的公历时刻
 * @param endTime 结束(即开始起运)的公历时刻
 * @param yearCount 年数
 * @param monthCount 月数
 * @param dayCount 日数
 * @param hourCount 小时数
 * @param minuteCount 分钟数
 * @author 6tail
 */
data class ChildLimitInfo(
    val startTime: SolarTime,
    val endTime: SolarTime,
    val yearCount: Int,
    val monthCount: Int,
    val dayCount: Int,
    val hourCount: Int,
    val minuteCount: Int
)

package com.tyme.unit

import kotlin.jvm.JvmStatic

/**
 * 周
 *
 * @author 6tail
 */
abstract class WeekUnit(
    /** 年 */
    year: Int,
    /** 月 */
    month: Int,
    /** 索引，0-5 */
    val index: Int,
    /** 起始星期，1234560分别代表星期一至星期天 */
    val start: Int,
) : MonthUnit(year, month) {
    companion object {
        val NAMES: Array<String> = arrayOf("第一周", "第二周", "第三周", "第四周", "第五周", "第六周")

        @JvmStatic
        fun validate(index: Int, start: Int) {
            if (index !in 0..5) {
                throw IllegalArgumentException("illegal week index: $index")
            }
            if (start !in 0..6) {
                throw IllegalArgumentException("illegal week start: $start")
            }
        }
    }
}

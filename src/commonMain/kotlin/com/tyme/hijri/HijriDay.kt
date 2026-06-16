package com.tyme.hijri

import com.tyme.jd.JulianDay
import com.tyme.solar.SolarDay
import com.tyme.unit.DayUnit
import kotlin.jvm.JvmStatic


/**
 * 回历日（公元622年7月16日为伊斯兰历元年元旦）
 *
 * @author 6tail
 */
class HijriDay(
    year: Int,
    month: Int,
    day: Int
) : DayUnit(year, month, day) {

    init {
        validate(year, month, day)
    }

    /**
     * 回历月
     *
     * @return 回历月
     */
    fun getHijriMonth(): HijriMonth {
        return HijriMonth(year, month)
    }

    override fun getName(): String {
        return NAMES[day - 1]
    }

    override fun toString(): String {
        return this.getHijriMonth().toString() + getName()
    }

    override fun next(n: Int): HijriDay {
        return getSolarDay().next(n).getHijriDay()
    }

    /**
     * 是否在指定回历日之前
     *
     * @param target 回历日
     * @return true/false
     */
    fun isBefore(target: HijriDay): Boolean {
        return getCompareIndex() < target.getCompareIndex()
    }

    /**
     * 是否在指定回历日之后
     *
     * @param target 回历日
     * @return true/false
     */
    fun isAfter(target: HijriDay): Boolean {
        return getCompareIndex() > target.getCompareIndex()
    }

    /**
     * 位于当年的索引
     *
     * @return 索引
     */
    fun getIndexInYear(): Int {
        return subtract(HijriDay(year, 1, 1))
    }

    /**
     * 回历日期相减，获得相差天数
     *
     * @param target 回历日
     * @return 天数
     */
    fun subtract(target: HijriDay): Int {
        return (this.getJulianDay().subtract(target.getJulianDay())).toInt()
    }

    /**
     * 儒略日
     *
     * @return 儒略日
     */
    fun getJulianDay(): JulianDay {
        return JulianDay(((11 * year + 3).floorDiv(30) + 354 * year + 30 * month - (month - 1).floorDiv(2) + day + 1948055).toDouble())
    }

    /**
     * 公历日
     *
     * @return 公历日
     */
    fun getSolarDay(): SolarDay {
        return SolarDay(622, 7, 16).next(subtract(HijriDay(1, 1, 1)))
    }

    override fun equals(other: Any?): Boolean {
        return other is HijriDay && toString() == other.toString()
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    companion object {
        val NAMES: Array<String> = arrayOf<String>("1日", "2日", "3日", "4日", "5日", "6日", "7日", "8日", "9日", "10日", "11日", "12日", "13日", "14日", "15日", "16日", "17日", "18日", "19日", "20日", "21日", "22日", "23日", "24日", "25日", "26日", "27日", "28日", "29日", "30日")

        @JvmStatic
        fun validate(year: Int, month: Int, day: Int) {
            if (day < 1 || day > HijriMonth(year, month).getDayCount()) {
                throw IllegalArgumentException("illegal hijri day: ${year}-${month}-${day}")
            }
        }

        @JvmStatic
        fun fromYmd(year: Int, month: Int, day: Int): HijriDay {
            return HijriDay(year, month, day)
        }
    }
}
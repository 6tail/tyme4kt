package com.tyme.lunar

import com.tyme.culture.Direction
import com.tyme.culture.fetus.FetusMonth
import com.tyme.culture.ren.MinorRen
import com.tyme.culture.star.nine.NineStar
import com.tyme.jd.JulianDay
import com.tyme.sixtycycle.SixtyCycle
import com.tyme.solar.SolarTerm
import com.tyme.unit.MonthUnit
import com.tyme.util.ShouXingUtil
import kotlin.jvm.JvmStatic
import kotlin.math.abs
import kotlin.math.ceil

/**
 * 农历月
 *
 * @author 6tail
 */
class LunarMonth(
    year: Int,
    month: Int,
) : MonthUnit(year, abs(month)) {

    /** 是否闰月 */
    private var leap: Boolean

    init {
        validate(year, month)
        leap = month < 0
    }

    protected fun getNewMoon(): Double {
        // 冬至
        val dongZhiJd = SolarTerm.fromIndex(year, 0).getCursoryJulianDay()

        // 冬至前的初一，今年首朔的日月黄经差
        var w: Double = ShouXingUtil.calcShuo(dongZhiJd)
        if (w > dongZhiJd) {
            w -= 29.53
        }

        // 正常情况正月初一为第3个朔日，但有些特殊的
        var offset = 2
        if (year in 9..<24) {
            offset = 1
        } else if (LunarYear.fromYear(year - 1).getLeapMonth() > 10 && year != 239 && year != 240) {
            offset = 3
        }

        // 本月初一
        return w + 29.5306 * (offset + getIndexInYear())
    }

    /**
     * 农历年
     *
     * @return 农历年
     */
    fun getLunarYear(): LunarYear {
        return LunarYear(year)
    }

    /**
     * 月
     *
     * @return 月，当月为闰月时，返回负数
     */
    fun getMonthWithLeap(): Int {
        return if (leap) -month else month
    }

    /**
     * 天数(大月30天，小月29天)
     *
     * @return 天数
     */
    fun getDayCount(): Int{
        val w = getNewMoon()
        // 本月天数 = 下月初一 - 本月初一
        return (ShouXingUtil.calcShuo(w + 29.5306) - ShouXingUtil.calcShuo(w)).toInt()
    }

    /**
     * 位于当年的索引(0-12)
     *
     * @return 索引
     */
    fun getIndexInYear(): Int{
        var index: Int = month - 1
        if (leap) {
            index += 1
        } else {
            val leapMonth = getLunarYear().getLeapMonth()
            if (leapMonth in 1..<month) {
                index += 1
            }
        }
        return index
    }

    /**
     * 农历季节
     *
     * @return 农历季节
     */
    fun getSeason(): LunarSeason {
        return LunarSeason(month - 1)
    }

    /**
     * 初一的儒略日
     *
     * @return 儒略日
     */
    fun getFirstJulianDay(): JulianDay{
        return JulianDay.fromJulianDay(JulianDay.J2000 + ShouXingUtil.calcShuo(getNewMoon()))
    }

    /**
     * 是否闰月
     *
     * @return true/false
     */
    fun isLeap(): Boolean{
        return leap
    }

    /**
     * 周数
     *
     * @param start 起始星期，1234560分别代表星期一至星期天
     * @return 周数
     */
    fun getWeekCount(start: Int): Int {
        return ceil((indexOf(getFirstJulianDay().getWeek().getIndex() - start, 7) + getDayCount()) / 7.0).toInt()
    }

    /**
     * 依据国家标准《农历的编算和颁行》GB/T 33661-2017中农历月的命名方法。
     *
     * @return 名称
     */
    override fun getName(): String {
        return (if (leap) "闰" else "") + NAMES[month - 1]
    }

    override fun toString(): String {
        return getLunarYear().toString() + getName()
    }

    override fun next(n: Int): LunarMonth {
        if (n == 0) {
            return fromYm(year, getMonthWithLeap())
        }
        var m: Int = getIndexInYear() + 1 + n
        var y: LunarYear = getLunarYear()
        if (n > 0) {
            var monthCount: Int = y.getMonthCount()
            while (m > monthCount) {
                m -= monthCount
                y = y.next(1)
                monthCount = y.getMonthCount()
            }
        } else {
            while (m <= 0) {
                y = y.next(-1)
                m += y.getMonthCount()
            }
        }
        var leap = false
        val leapMonth = y.getLeapMonth()
        if (leapMonth > 0) {
            if (m == leapMonth + 1) {
                leap = true
            }
            if (m > leapMonth) {
                m--
            }
        }
        return fromYm(y.year, if (leap) -m else m)
    }

    /**
     * 本月的农历日列表
     *
     * @return 农历日列表
     */
    fun getDays(): List<LunarDay> {
        val size: Int = getDayCount()
        val m: Int = getMonthWithLeap()
        val l: MutableList<LunarDay> = ArrayList(size)
        for (i in 1..size) {
            l.add(LunarDay(year, m, i))
        }
        return l
    }

    fun getFirstDay(): LunarDay {
        return LunarDay.fromYmd(year, getMonthWithLeap(), 1)
    }

    /**
     * 本月的农历周列表
     *
     * @param start 星期几作为一周的开始，1234560分别代表星期一至星期天
     * @return 周列表
     */
    fun getWeeks(start: Int): List<LunarWeek> {
        val size: Int = getWeekCount(start)
        val m: Int = getMonthWithLeap()
        val l: MutableList<LunarWeek> = ArrayList(size)
        for (i in 0 until size) {
            l.add(LunarWeek(year, m, i, start))
        }
        return l
    }

    /**
     * 干支
     *
     * @return 干支
     */
     fun getSixtyCycle(): SixtyCycle {
         return SixtyCycle(year * 12 + month - 47)
     }

     /**
      * 九星
      *
      * @return 九星
      */
    fun getNineStar():NineStar {
         var index: Int = getSixtyCycle().getEarthBranch().getIndex()
         if (index < 2) {
             index += 3
         }
         return NineStar(27 - getLunarYear().getSixtyCycle().getEarthBranch().getIndex() % 3 * 3 - index)
    }

    /**
     * 太岁方位
     *
     * @return 方位
     */
    fun getJupiterDirection(): Direction {
        val sixtyCycle: SixtyCycle = getSixtyCycle()
        val n: Int = intArrayOf(7, -1, 1, 3)[sixtyCycle.getEarthBranch().next(-2).getIndex() % 4]
        return if (n != -1) Direction(n) else sixtyCycle.getHeavenStem().getDirection()
    }

    /**
     * 逐月胎神
     *
     * @return 逐月胎神
     */
    fun getFetus(): FetusMonth? {
        return FetusMonth.fromLunarMonth(this)
    }

    /**
     * 小六壬
     *
     * @return 小六壬
     */
    fun getMinorRen():MinorRen {
        return MinorRen((month - 1) % 6)
    }

    override fun equals(other: Any?): Boolean {
        return other is LunarMonth && toString() == other.toString()
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    companion object {
        val NAMES: Array<String> = arrayOf("正月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月")

        @JvmStatic
        fun validate(year: Int, month: Int) {
            if (month == 0 || month > 12 || month < -12) {
                throw IllegalArgumentException("illegal lunar month: $month")
            }
            // 闰月检查
            if (month < 0 && -month != LunarYear.fromYear(year).getLeapMonth()) {
                throw IllegalArgumentException("illegal leap month -$month in lunar year $year")
            }
        }

        /**
         * 从农历年月初始化
         *
         * @param year  农历年
         * @param month 农历月，闰月为负
         * @return 农历月
         */
        @JvmStatic
        fun fromYm(year: Int, month: Int): LunarMonth {
            return LunarMonth(year, month)
        }
    }
}

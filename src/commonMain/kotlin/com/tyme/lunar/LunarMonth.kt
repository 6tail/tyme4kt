package com.tyme.lunar

import com.tyme.culture.Direction
import com.tyme.culture.fetus.FetusMonth
import com.tyme.culture.ren.MinorRen
import com.tyme.culture.star.nine.NineStar
import com.tyme.jd.JulianDay
import com.tyme.sixtycycle.SixtyCycle
import com.tyme.solar.SolarTerm
import com.tyme.unit.AbstractDay
import com.tyme.unit.AbstractLeapMonth
import com.tyme.unit.AbstractYear
import com.tyme.util.ShouXingUtil
import kotlin.jvm.JvmStatic

/**
 * 农历月
 *
 * @author 6tail
 */
class LunarMonth(year: Int, month: Int): AbstractLeapMonth(year, month) {

    init {
        validate(year, month)
    }

    private fun getNewMoon(): Double {
        val m: Int = indexInYear
        var months: DoubleArray? = NEW_MOON_CACHE[year]
        if (months == null) {
            months = DoubleArray(13) { Double.NaN }
            NEW_MOON_CACHE[year] = months
        }
        var v = months[m]
        if (v.isNaN()) {
            // 冬至
            val dongZhiJd = SolarTerm(year, 0).cursoryJulianDay

            // 冬至前的初一，今年首朔的日月黄经差
            var w = ShouXingUtil.calcShuo(dongZhiJd)
            if (w > dongZhiJd) {
                w -= 29.53
            }

            // 正常情况正月初一为第3个朔日，但有些特殊的
            var offset = 2
            if (year in 9..<24) {
                offset = 1
            } else if (LunarYear(year - 1).leapMonth > 10 && year != 239 && year != 240) {
                offset = 3
            }

            // 本月初一
            v = w + 29.5306 * (offset + m)
            months[m] = v
        }
        return v
    }

    /**
     * 农历年
     */
    val lunarYear: LunarYear
        get() = LunarYear(year)

    override val abstractYear: AbstractYear
        get() = lunarYear

    override val dayCount: Int
        get() {
            // 大月30天，小月29天
            val w = getNewMoon()
            // 本月天数 = 下月初一 - 本月初一
            return (ShouXingUtil.calcShuo(w + 29.5306) - ShouXingUtil.calcShuo(w)).toInt()
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
    fun getFirstJulianDay(): JulianDay {
        return JulianDay(JulianDay.J2000 + ShouXingUtil.calcShuo(getNewMoon()))
    }

    /**
     * 依据国家标准《农历的编算和颁行》GB/T 33661-2017中农历月的命名方法。
     *
     * @return 名称
     */
    override fun getName(): String {
        return (if (leap) "闰" else "") + NAMES[month - 1]
    }

    override fun next(n: Int): LunarMonth {
        val m = super.next(n)
        return LunarMonth(m.year, m.monthValue)
    }

    /**
     * 本月的农历日列表
     *
     * @return 农历日列表
     */
    fun getDays(): List<LunarDay> {
        val size: Int = dayCount
        val m: Int = monthValue
        val l: MutableList<LunarDay> = ArrayList(size)
        for (i in 1..size) {
            l.add(LunarDay(year, m, i))
        }
        return l
    }

    /**
     * 初一
     */
    override val firstDay: AbstractDay
        get() = LunarDay(year, monthValue, 1)

    /**
     * 本月的农历周列表
     *
     * @param start 星期几作为一周的开始，1234560分别代表星期一至星期天
     * @return 周列表
     */
    fun getWeeks(start: Int): List<LunarWeek> {
        val size: Int = getWeekCount(start)
        val m: Int = monthValue
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
    fun getNineStar(): NineStar {
         var index: Int = getSixtyCycle().getEarthBranch().index
         if (index < 2) {
             index += 3
         }
         return NineStar(27 - lunarYear.getSixtyCycle().getEarthBranch().index % 3 * 3 - index)
    }

    /**
     * 太岁方位
     *
     * @return 方位
     */
    fun getJupiterDirection(): Direction {
        val sixtyCycle: SixtyCycle = getSixtyCycle()
        val n: Int = intArrayOf(7, -1, 1, 3)[sixtyCycle.getEarthBranch().next(-2).index % 4]
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

    companion object {
        val NAMES: Array<String> = arrayOf("正月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月")
        private val NEW_MOON_CACHE: Array<DoubleArray?> = arrayOfNulls<DoubleArray>(10000)

        @JvmStatic
        fun validate(year: Int, month: Int) {
            if (month == 0 || month > 12 || month < -12) {
                throw IllegalArgumentException("illegal lunar month: $month")
            }
            // 闰月检查
            if (month < 0 && -month != LunarYear(year).leapMonth) {
                throw IllegalArgumentException("illegal leap month ${-month} in lunar year $year")
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

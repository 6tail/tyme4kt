package com.tyme.lunar

import com.tyme.AbstractTyme
import com.tyme.culture.Direction
import com.tyme.culture.fetus.FetusMonth
import com.tyme.culture.ren.MinorRen
import com.tyme.culture.star.nine.NineStar
import com.tyme.jd.JulianDay
import com.tyme.sixtycycle.EarthBranch
import com.tyme.sixtycycle.HeavenStem
import com.tyme.sixtycycle.SixtyCycle
import com.tyme.solar.SolarTerm
import com.tyme.util.ShouXingUtil
import kotlin.jvm.JvmStatic
import kotlin.math.abs
import kotlin.math.ceil

/**
 * 农历月
 *
 * @author 6tail
 */
class LunarMonth : AbstractTyme {

    /** 农历年 */
    private var year: LunarYear
    /** 月  */
    private var month: Int
    /** 是否闰月 */
    private var leap: Boolean
    /** 天数 */
    private var dayCount: Int
    /** 位于当年的索引，0-12 */
    private var indexInYear: Int
    /** 初一的儒略日 */
    private var firstJulianDay: JulianDay

    /**
     * 从缓存初始化
     *
     * @param cache 缓存[农历年(int)，农历月(int,闰月为负)，天数(int)，位于当年的索引(int)，初一的儒略日(double)]
     */
    protected constructor(cache: Array<Double>) {
        val m: Int = cache[1].toInt()
        year = LunarYear(cache[0].toInt())
        month = abs(m)
        leap = m < 0
        dayCount = cache[2].toInt()
        indexInYear = cache[3].toInt()
        firstJulianDay = JulianDay(cache[4])
    }

    /**
     * 从农历年月初始化
     *
     * @param year  农历年
     * @param month 农历月，闰月为负
     */
   protected constructor(year: Int, month: Int) {
        require(!(month == 0 || month > 12 || month < -12)) { "illegal lunar month: $month" }
        val currentYear = LunarYear(year)
        val y: Int = currentYear.getYear()
        val currentLeapMonth: Int = currentYear.getLeapMonth()
        val leap: Boolean = month < 0
        val m: Int = abs(month)
        require(!(leap && m != currentLeapMonth)) { "illegal leap month $m in lunar year $year" }

        // 冬至
        val dongZhiJd: Double = SolarTerm(year, 0).getCursoryJulianDay()

        // 冬至前的初一，今年首朔的日月黄经差
        var w: Double = ShouXingUtil.calcShuo(dongZhiJd)
        if (w > dongZhiJd) {
            w -= 29.53
        }

        // 正常情况正月初一为第3个朔日，但有些特殊的
        var offset = 2
        if (y in 9..23) {
            offset = 1
        } else if (LunarYear(year - 1).getLeapMonth() > 10 && y != 239 && y != 240) {
            offset = 3
        }

        // 位于当年的索引
        var index: Int = m - 1
        if (leap || (currentLeapMonth in 1..< m)) {
            index += 1
        }
        indexInYear = index

        // 本月初一
        w += 29.5306 * (offset + index)
        val firstDay: Double = ShouXingUtil.calcShuo(w)
        firstJulianDay = JulianDay(JulianDay.J2000 + firstDay)
        // 本月天数 = 下月初一 - 本月初一
        dayCount = (ShouXingUtil.calcShuo(w + 29.5306) - firstDay).toInt()
        this.year = currentYear
        this.month = m
        this.leap = leap
    }

    /**
     * 农历年
     *
     * @return 农历年
     */
    fun getLunarYear(): LunarYear {
        return year
    }

    /**
     * 年
     *
     * @return 年
     */
    fun getYear(): Int {
        return year.getYear()
    }

    /**
     * 月
     *
     * @return 月
     */
    fun getMonth(): Int{
        return month
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
        return dayCount
    }

    /**
     * 位于当年的索引(0-12)
     *
     * @return 索引
     */
    fun getIndexInYear(): Int{
        return indexInYear
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
        return firstJulianDay
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
        return ceil((indexOf(firstJulianDay.getWeek().getIndex() - start, 7) + getDayCount()) / 7.0).toInt()
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
        return year.toString() + getName()
    }

    override fun next(n: Int): LunarMonth {
        if (n == 0) {
            return fromYm(getYear(), getMonthWithLeap())
        }
        var m: Int = indexInYear + 1 + n
        var y: LunarYear = year
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
        return fromYm(y.getYear(), if (leap) -m else m)
    }

    /**
     * 本月的农历日列表
     *
     * @return 农历日列表
     */
    fun getDays(): List<LunarDay> {
        val size: Int = dayCount
        val y: Int = getYear()
        val m: Int = getMonthWithLeap()
        val l: MutableList<LunarDay> = ArrayList(size)
        for (i in 1..size) {
            l.add(LunarDay(y, m, i))
        }
        return l
    }

    /**
     * 本月的农历周列表
     *
     * @param start 星期几作为一周的开始，1234560分别代表星期一至星期天
     * @return 周列表
     */
    fun getWeeks(start: Int): List<LunarWeek> {
        val size: Int = getWeekCount(start)
        val y: Int = getYear()
        val m: Int = getMonthWithLeap()
        val l: MutableList<LunarWeek> = ArrayList(size)
        for (i in 0 until size) {
            l.add(LunarWeek(y, m, i, start))
        }
        return l
    }

    /**
     * 干支
     *
     * @return 干支
     */
     fun getSixtyCycle(): SixtyCycle {
         return SixtyCycle(HeavenStem(year.getSixtyCycle().getHeavenStem().getIndex() * 2 + month + 1).getName() + EarthBranch(month + 1).getName())
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
         return NineStar(27 - year.getSixtyCycle().getEarthBranch().getIndex() % 3 * 3 - index)
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
        /** 缓存 */
       protected val cache: MutableMap<String, Array<Double>> = HashMap()

        val NAMES: Array<String> = arrayOf("正月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月")

        /**
         * 从农历年月初始化
         *
         * @param year  农历年
         * @param month 农历月，闰月为负
         * @return 农历月
         */
        @JvmStatic
        fun fromYm(year: Int, month: Int): LunarMonth {
            val m: LunarMonth
            val key = "${year}${month}"
            val c: Array<Double>? = cache[key]
            if (null != c) {
                m = LunarMonth(c)
            } else {
                m = LunarMonth(year, month)
                cache[key] = arrayOf(m.getYear().toDouble(), m.getMonthWithLeap().toDouble(), m.getDayCount().toDouble(), m.getIndexInYear().toDouble(), m.getFirstJulianDay().getDay())
            }
            return m
        }
    }
}

package com.tyme.lunar

import com.tyme.AbstractTyme
import com.tyme.culture.Taboo
import com.tyme.culture.ren.MinorRen
import com.tyme.culture.star.nine.NineStar
import com.tyme.culture.star.twelve.TwelveStar
import com.tyme.eightchar.EightChar
import com.tyme.eightchar.provider.EightCharProvider
import com.tyme.eightchar.provider.impl.DefaultEightCharProvider
import com.tyme.sixtycycle.EarthBranch
import com.tyme.sixtycycle.HeavenStem
import com.tyme.sixtycycle.SixtyCycle
import com.tyme.sixtycycle.SixtyCycleHour
import com.tyme.solar.SolarDay
import com.tyme.solar.SolarTerm
import com.tyme.solar.SolarTime
import kotlin.jvm.JvmStatic
import kotlin.math.abs

/**
 * 农历时辰
 *
 * @author 6tail
 */
class LunarHour(
    /** 农历年 */
    year: Int,
    /** 农历月，闰月为负 */
    month: Int,
    /** 农历日 */
    day: Int,
    /** 时 */
    private var hour: Int,
    /** 分 */
    private var minute: Int,
    /** 秒 */
    private var second: Int
) : AbstractTyme() {
    /** 农历日 */
    private var day: LunarDay
    /** 公历时刻（第一次使用时才会初始化） */
    private var solarTime: SolarTime? = null
    /** 干支时辰（第一次使用时才会初始化） */
    private var sixtyCycleHour: SixtyCycleHour? = null

    init {
        require(hour in 0..23) { "illegal hour: $hour" }
        require(minute in 0 .. 59) { "illegal minute: $minute" }
        require(second in 0 .. 59) { "illegal second: $second" }
        this.day = LunarDay(year, month, day)
    }

    /**
     * 农历日
     *
     * @return 农历日
     */
    fun getLunarDay(): LunarDay {
        return day
    }

    /**
     * 年
     *
     * @return 年
     */
    fun getYear(): Int {
        return day.getYear()
    }

    /**
     * 月
     *
     * @return 月
     */
    fun getMonth(): Int {
        return day.getMonth()
    }

    /**
     * 日
     *
     * @return 日
     */
    fun getDay(): Int {
        return day.getDay()
    }

    /**
     * 时
     *
     * @return 时
     */
    fun getHour(): Int{
        return hour
    }

    /**
     * 分
     *
     * @return 分
     */
    fun getMinute(): Int{
        return minute
    }

    /**
     * 秒
     *
     * @return 秒
     */
    fun getSecond(): Int{
        return second
    }

    override fun getName(): String {
        return "${EarthBranch(getIndexInDay()).getName()}时"
    }

    override fun toString(): String {
        return "${day}${getSixtyCycle().getName()}时"
    }

    /**
     * 位于当天的索引
     *
     * @return 索引
     */
    fun getIndexInDay(): Int {
        return (hour + 1) / 2
    }

    override fun next(n: Int): LunarHour {
        if (n == 0) {
            return LunarHour(getYear(), getMonth(), getDay(), hour, minute, second)
        }
        val h: Int = hour + n * 2
        val diff: Int = if (h < 0) -1 else 1
        var hour: Int = abs(h)
        var days: Int = hour / 24 * diff
        hour = (hour % 24) * diff
        if (hour < 0) {
            hour += 24
            days--
        }
        val d: LunarDay = day.next(days)
        return LunarHour(d.getYear(), d.getMonth(), d.getDay(), hour, minute, second)
    }

    /**
     * 是否在指定农历时辰之前
     *
     * @param target 农历时辰
     * @return true/false
     */
    fun isBefore(target: LunarHour): Boolean {
        if (day != target.getLunarDay()) {
            return day.isBefore(target.getLunarDay())
        }
        if (hour != target.getHour()) {
            return hour < target.getHour()
        }
        return if (minute != target.getMinute()) minute < target.getMinute() else second < target.getSecond()
    }

    /**
     * 是否在指定农历时辰之后
     *
     * @param target 农历时辰
     * @return true/false
     */
    fun isAfter(target: LunarHour): Boolean {
        if (day != target.getLunarDay()) {
            return day.isAfter(target.getLunarDay())
        }
        if (hour != target.getHour()) {
            return hour > target.getHour()
        }
        return if (minute != target.getMinute()) minute > target.getMinute() else second > target.getSecond()
    }

    /**
     * 干支
     *
     * @return 干支
     */
    fun getSixtyCycle(): SixtyCycle {
        val earthBranchIndex: Int = getIndexInDay() % 12
        var d: SixtyCycle = day.getSixtyCycle()
        if (hour >= 23) {
            d = d.next(1)
        }
        val heavenStemIndex: Int = d.getHeavenStem().getIndex() % 5 * 2 + earthBranchIndex
        return SixtyCycle(HeavenStem(heavenStemIndex).getName() + EarthBranch(earthBranchIndex).getName())
    }

    /**
     * 黄道黑道十二神
     *
     * @return 黄道黑道十二神
     */
    fun getTwelveStar(): TwelveStar {
        return TwelveStar(getSixtyCycle().getEarthBranch().getIndex() + (8 - getSixtyCycleHour().getDay().getEarthBranch().getIndex() % 6) * 2)
    }

    /**
     * 九星（时家紫白星歌诀：三元时白最为佳，冬至阳生顺莫差，孟日七宫仲一白，季日四绿发萌芽，每把时辰起甲子，本时星耀照光华，时星移入中宫去，顺飞八方逐细查。夏至阴生逆回首，孟归三碧季加六，仲在九宫时起甲，依然掌中逆轮跨。）
     *
     * @return 九星
     */
    fun getNineStar(): NineStar {
        val solar: SolarDay = day.getSolarDay()
        val dongZhi = SolarTerm(solar.getYear(), 0)
        val xiaZhi = dongZhi.next(12)
        val asc: Boolean = !solar.isBefore(dongZhi.getJulianDay().getSolarDay()) && solar.isBefore(xiaZhi.getJulianDay().getSolarDay())
        var start: Int = intArrayOf(8, 5, 2)[day.getSixtyCycle().getEarthBranch().getIndex() % 3]
        if (asc) {
            start = 8 - start
        }
        val earthBranchIndex: Int = getIndexInDay() % 12
        return NineStar(start + (if (asc) earthBranchIndex else -earthBranchIndex))
    }

    /**
     * 公历时刻
     *
     * @return 公历时刻
     */
    fun getSolarTime(): SolarTime {
        if (solarTime == null) {
            val d: SolarDay = day.getSolarDay()
            solarTime = SolarTime(d.getYear(), d.getMonth(), d.getDay(), hour, minute, second)
        }
        return solarTime!!
    }

    /**
     * 干支时辰
     *
     * @return 干支时辰
     */
    fun getSixtyCycleHour(): SixtyCycleHour {
        if (sixtyCycleHour == null) {
            sixtyCycleHour = getSolarTime().getSixtyCycleHour()
        }
        return sixtyCycleHour!!
    }

    /**
     * 八字
     *
     * @return 八字
     */
    fun getEightChar(): EightChar {
        return provider.getEightChar(this)
    }

    /**
     * 宜
     *
     * @return 宜忌列表
     */
    fun getRecommends(): List<Taboo> {
        return Taboo.getHourRecommends(getSixtyCycleHour().getDay(), getSixtyCycle())
    }

    /**
     * 忌
     *
     * @return 宜忌列表
     */
    fun getAvoids(): List<Taboo> {
        return Taboo.getHourAvoids(getSixtyCycleHour().getDay(), getSixtyCycle())
    }

    /**
     * 小六壬
     *
     * @return 小六壬
     */
    fun getMinorRen(): MinorRen {
        return day.getMinorRen().next(getIndexInDay())
    }

    override fun equals(other: Any?): Boolean {
        return other is LunarHour && toString() == other.toString()
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    companion object {
        /** 八字计算接口 */
        var provider: EightCharProvider = DefaultEightCharProvider()

        @JvmStatic
        fun fromYmdHms(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int): LunarHour {
            return LunarHour(year, month, day, hour, minute, second)
        }
    }
}

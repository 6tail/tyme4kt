package com.tyme.solar

import com.tyme.AbstractTyme
import com.tyme.culture.Constellation
import com.tyme.culture.Week
import com.tyme.culture.dog.Dog
import com.tyme.culture.dog.DogDay
import com.tyme.culture.nine.Nine
import com.tyme.culture.nine.NineDay
import com.tyme.culture.phenology.Phenology
import com.tyme.culture.phenology.PhenologyDay
import com.tyme.culture.plumrain.PlumRain
import com.tyme.culture.plumrain.PlumRainDay
import com.tyme.enums.HideHeavenStemType
import com.tyme.festival.SolarFestival
import com.tyme.holiday.LegalHoliday
import com.tyme.jd.JulianDay
import com.tyme.lunar.LunarDay
import com.tyme.lunar.LunarMonth
import com.tyme.rabbyung.RabByungDay
import com.tyme.sixtycycle.HideHeavenStem
import com.tyme.sixtycycle.HideHeavenStemDay
import com.tyme.sixtycycle.SixtyCycleDay
import kotlin.jvm.JvmStatic
import kotlin.math.ceil


/**
 * 公历日
 *
 * @author 6tail
 */
class SolarDay(
    /** 年 */
    year: Int,
    /** 月 */
    month: Int,
    /** 日 */
    private var day: Int
) : AbstractTyme() {

    /** 公历月 */
    private var month: SolarMonth

    init {
        require(day >= 1) { "illegal solar day: ${year}-${month}-${day}" }
        val m = SolarMonth(year, month)
        if (1582 == year && 10 == month) {
            require(!((day in 5..14) || day > 31)) { "illegal solar day: ${year}-${month}-${day}" }
        } else {
            require(day <= m.getDayCount()) { "illegal solar day: ${year}-${month}-${day}" }
        }
        this.month = m
    }

    fun getSolarMonth(): SolarMonth {
        return month
    }

    /**
     * 年
     *
     * @return 年
     */
    fun getYear(): Int {
        return month.getYear()
    }

    /**
     * 月
     *
     * @return 月
     */
    fun getMonth(): Int {
        return month.getMonth()
    }

    /**
     * 日
     *
     * @return 日
     */
    fun getDay(): Int {
        return day
    }

    /**
     * 星期
     *
     * @return 星期
     */
    fun getWeek(): Week {
        return getJulianDay().getWeek()
    }

    /**
     * 星座
     *
     * @return 星座
     */
    fun getConstellation(): Constellation {
        val y = getMonth() * 100 + day
        return Constellation(if (y > 1221 || y < 120) 9 else if (y < 219) 10 else if (y < 321) 11 else if (y < 420) 0 else if (y < 521) 1 else if (y < 622) 2 else if (y < 723) 3 else if (y < 823) 4 else if (y < 923) 5 else if (y < 1024) 6 else if (y < 1123) 7 else 8)
    }

    override fun getName(): String {
        return NAMES[day - 1]
    }

    override fun toString(): String {
        return month.toString() + getName()
    }

    override fun next(n: Int): SolarDay {
        return getJulianDay().next(n).getSolarDay()
    }

    /**
     * 是否在指定公历日之前
     *
     * @param target 公历日
     * @return true/false
     */
    fun isBefore(target: SolarDay): Boolean {
        val aYear: Int = getYear()
        val bYear: Int = target.getYear()
        if (aYear != bYear) {
            return aYear < bYear
        }
        val aMonth: Int = getMonth()
        val bMonth: Int = target.getMonth()
        return if (aMonth != bMonth) aMonth < bMonth else day < target.getDay()
    }

    /**
     * 是否在指定公历日之后
     *
     * @param target 公历日
     * @return true/false
     */
    fun isAfter(target: SolarDay): Boolean {
        val aYear: Int = getYear()
        val bYear: Int = target.getYear()
        if (aYear != bYear) {
            return aYear > bYear
        }
        val aMonth = getMonth()
        val bMonth = target.getMonth()
        return if (aMonth != bMonth) aMonth > bMonth else day > target.getDay()
    }

    /**
     * 节气
     *
     * @return 节气
     */
    fun getTerm(): SolarTerm {
        return getTermDay().getSolarTerm()
    }

    /**
     * 节气第几天
     *
     * @return 节气第几天
     */
    fun getTermDay(): SolarTermDay {
        var y: Int = getYear()
        var i: Int = getMonth() * 2
        if (i == 24) {
            y += 1
            i = 0
        }
        var term = SolarTerm(y, i)
        var day: SolarDay = term.getJulianDay().getSolarDay()
        while (isBefore(day)) {
            term = term.next(-1)
            day = term.getJulianDay().getSolarDay()
        }
        return SolarTermDay(term, subtract(day))
    }

    /**
     * 公历周
     *
     * @param start 起始星期，1234560分别代表星期一至星期天
     * @return 公历周
     */
    fun getSolarWeek(start: Int): SolarWeek {
        val y: Int = getYear()
        val m: Int = getMonth()
        return SolarWeek(y, m, ceil((day + SolarDay(y, m, 1).getWeek().next(-start).getIndex()) / 7.0).toInt() - 1, start)
    }

    /**
     * 候
     *
     * @return 候
     */
    fun getPhenology(): Phenology {
        return getPhenologyDay().getPhenology()
    }

    /**
     * 七十二候
     *
     * @return 七十二候
     */
    fun getPhenologyDay(): PhenologyDay {
        val d: SolarTermDay = getTermDay()
        val dayIndex: Int = d.getDayIndex()
        var index: Int = dayIndex / 5
        if (index > 2) {
            index = 2
        }
        val term: SolarTerm = d.getSolarTerm()
        return PhenologyDay(Phenology(term.getYear(), term.getIndex() * 3 + index), dayIndex - index * 5)
    }

    /**
     * 三伏天
     *
     * @return 三伏天
     */
    fun getDogDay(): DogDay? {
        // 夏至
        val xiaZhi = SolarTerm(getYear(), 12)
        // 第1个庚日
        var start: SolarDay = xiaZhi.getJulianDay().getSolarDay()
        // 第3个庚日，即初伏第1天
        start = start.next(start.getLunarDay().getSixtyCycle().getHeavenStem().stepsTo(6) + 20)
        var days: Int = subtract(start)
        // 初伏以前
        if (days < 0) {
            return null
        }
        if (days < 10) {
            return DogDay(Dog(0), days)
        }
        // 第4个庚日，中伏第1天
        start = start.next(10)
        days = subtract(start)
        if (days < 10) {
            return DogDay(Dog(1), days)
        }
        // 第5个庚日，中伏第11天或末伏第1天
        start = start.next(10)
        days = subtract(start)
        // 立秋
        if (xiaZhi.next(3).getJulianDay().getSolarDay().isAfter(start)) {
            if (days < 10) {
                return DogDay(Dog(1), days + 10)
            }
            start = start.next(10)
            days = subtract(start)
        }
        return if (days >= 10) null else DogDay(Dog(2), days)
    }

    /**
     * 数九天
     *
     * @return 数九天
     */
    fun getNineDay(): NineDay? {
        val year: Int = getYear()
        var start: SolarDay = SolarTerm(year + 1, 0).getJulianDay().getSolarDay()
        if (isBefore(start)) {
            start = SolarTerm(year, 0).getJulianDay().getSolarDay()
        }
        val end = start.next(81)
        if (isBefore(start) || !isBefore(end)) {
            return null
        }
        val days = subtract(start)
        return NineDay(Nine(days / 9), days % 9)
    }

    /**
     * 梅雨天（芒种后的第1个丙日入梅，小暑后的第1个未日出梅）
     *
     * @return 梅雨天
     */
    fun getPlumRainDay(): PlumRainDay? {
        // 芒种
        val grainInEar = SolarTerm(getYear(), 11)
        var start: SolarDay = grainInEar.getJulianDay().getSolarDay()
        // 芒种后的第1个丙日
        start = start.next(start.getLunarDay().getSixtyCycle().getHeavenStem().stepsTo(2))
        // 小暑
        var end: SolarDay = grainInEar.next(2).getJulianDay().getSolarDay()
        // 小暑后的第1个未日
        end = end.next(end.getLunarDay().getSixtyCycle().getEarthBranch().stepsTo(7))

        if (isBefore(start) || isAfter(end)) {
            return null
        }
        return if (this == end) PlumRainDay(PlumRain(1), 0) else PlumRainDay(PlumRain(0), subtract(start))
    }

    /**
     * 人元司令分野
     *
     * @return 人元司令分野
     */
    fun getHideHeavenStemDay(): HideHeavenStemDay {
        val dayCounts: IntArray = intArrayOf(3, 5, 7, 9, 10, 30)
        var term: SolarTerm = getTerm()
        if (term.isQi()) {
            term = term.next(-1)
        }
        var dayIndex: Int = subtract(term.getJulianDay().getSolarDay())
        val startIndex: Int = (term.getIndex() - 1) * 3
        val data: String = "93705542220504xx1513904541632524533533105544806564xx7573304542018584xx95".substring(startIndex, startIndex + 6)
        var days = 0
        var heavenStemIndex = 0
        var typeIndex = 0
        while (typeIndex < 3) {
            val i: Int = typeIndex * 2
            val d: String = data.substring(i, i + 1)
            var count = 0
            if (d != "x") {
                heavenStemIndex = d.toInt(10)
                count = dayCounts[data.substring(i + 1, i + 2).toInt(10)]
                days += count
            }
            if (dayIndex <= days) {
                dayIndex -= days - count
                break
            }
            typeIndex++
        }
        val type: HideHeavenStemType = when (typeIndex) {
            0 -> HideHeavenStemType.RESIDUAL
            1 -> HideHeavenStemType.MIDDLE
            else -> HideHeavenStemType.MAIN
        }
        return HideHeavenStemDay(HideHeavenStem(heavenStemIndex, type), dayIndex)
    }

    /**
     * 位于当年的索引
     *
     * @return 索引
     */
    fun getIndexInYear(): Int {
        return subtract(SolarDay(getYear(), 1, 1))
    }

    /**
     * 公历日期相减，获得相差天数
     *
     * @param target 公历
     * @return 天数
     */
    fun subtract(target: SolarDay): Int {
        return (getJulianDay().subtract(target.getJulianDay())).toInt()
    }

    /**
     * 儒略日
     *
     * @return 儒略日
     */
    fun getJulianDay(): JulianDay {
        return JulianDay.fromYmdHms(getYear(), getMonth(), day, 0, 0, 0)
    }

    /**
     * 农历日
     *
     * @return 农历日
     */
    fun getLunarDay(): LunarDay {
        var m: LunarMonth = LunarMonth.fromYm(getYear(), getMonth())
        var days = subtract(m.getFirstJulianDay().getSolarDay())
        while (days < 0) {
            m = m.next(-1)
            days += m.getDayCount()
        }
        return LunarDay(m.getYear(), m.getMonthWithLeap(), days + 1)
    }

    /**
     * 干支日
     *
     * @return 干支日
     */
    fun getSixtyCycleDay(): SixtyCycleDay {
        return SixtyCycleDay(this)
    }

    /**
     * 藏历日
     *
     * @return 藏历日
     */
    fun getRabByungDay(): RabByungDay {
        return RabByungDay.fromSolarDay(this)
    }

    /**
     * 法定假日，如果当天不是法定假日，返回null
     *
     * @return 法定假日
     */
    fun getLegalHoliday(): LegalHoliday? {
        return LegalHoliday.fromYmd(getYear(), getMonth(), day)
    }

    /**
     * 公历现代节日，如果当天不是公历现代节日，返回null
     *
     * @return 公历现代节日
     */
    fun getFestival(): SolarFestival? {
        return SolarFestival.fromYmd(getYear(), getMonth(), day)
    }

    override fun equals(other: Any?): Boolean {
        return other is SolarDay && toString() == other.toString()
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    companion object {
        val NAMES: Array<String> = arrayOf("1日", "2日", "3日", "4日", "5日", "6日", "7日", "8日", "9日", "10日", "11日", "12日", "13日", "14日", "15日", "16日", "17日", "18日", "19日", "20日", "21日", "22日", "23日", "24日", "25日", "26日", "27日", "28日", "29日", "30日", "31日")

        @JvmStatic
        fun fromYmd(year: Int, month: Int, day: Int): SolarDay {
            return SolarDay(year, month, day)
        }
    }
}

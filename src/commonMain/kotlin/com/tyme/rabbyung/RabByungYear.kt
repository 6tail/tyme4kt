package com.tyme.rabbyung

import com.tyme.culture.Zodiac
import com.tyme.sixtycycle.SixtyCycle
import com.tyme.solar.SolarYear
import com.tyme.unit.AbstractTraditionalYear
import kotlin.jvm.JvmStatic

/**
 * 藏历年(公历1027年为藏历元年，第一饶迥火兔年）
 *
 * @author 6tail
 */
class RabByungYear(year: Int): AbstractTraditionalYear(year) {

    init {
        validate(year)
    }

    /**
     * 饶迥序号
     *
     * @return 数字，从0开始
     */
    fun getRabByungIndex(): Int {
        return (year - 1024) / 60
    }

    /**
     * 生肖
     *
     * @return 生肖
     */
    fun getZodiac(): Zodiac {
        return getSixtyCycle().getEarthBranch().getZodiac()
    }

    /**
     * 五行
     *
     * @return 藏历五行
     */
    fun getElement(): RabByungElement {
        return RabByungElement(getSixtyCycle().getHeavenStem().getElement().index)
    }

    /**
     * 名称
     *
     * @return 名称
     */
    override fun getName(): String {
        var n = getRabByungIndex() + 1
        val d = "零一二三四五六七八九"
        val s = if (n > 99) "${d[n / 100]}百" else ""
        n %= 100
        val middle = when {
            n == 0 -> s
            n < 10 -> s + (if (s.isEmpty()) "" else "零") + d[n]
            else -> {
                val tens = if (n < 20) {
                    if (s.isEmpty()) "" else "一"
                } else {
                    d[n / 10].toString()
                }
                val units = if (n % 10 > 0) d[n % 10].toString() else ""
                "$s${tens}十$units"
            }
        }
        return "第${middle}饶迥${getElement()}${getZodiac()}年"
    }

    override fun next(n: Int): RabByungYear {
        return RabByungYear(year + n)
    }

    override val leapMonth: Int
        get() {
            var y = 1
            var m = 4
            var t = 1
            while (y < year) {
                val i = m + 31 + t
                y += 2
                m = i - 23
                if (i > 35) {
                    y += 1
                    m -= 12
                }
                t = 1 - t
            }
            return if (y == year) m else 0
        }

    /**
     * 公历年
     *
     * @return 公历年
     */
    fun getSolarYear(): SolarYear {
        return SolarYear(year)
    }

    /**
     * 首月
     */
    val firstMonth: RabByungMonth
        get() = RabByungMonth(year, 1)

    /**
     * 藏历月列表
     *
     * @return 藏历月列表，一般有12个月，当年有闰月时，有13个月。
     */
    fun getMonths(): List<RabByungMonth> {
        return buildMonths { i ->
            RabByungMonth(year, i)
        }
    }

    companion object {
        @JvmStatic
        fun validate(year: Int) {
            validateRange(year, 1027, 9999, "rab-byung year")
        }

        @JvmStatic
        fun fromSixtyCycle(rabByungIndex: Int, sixtyCycle: SixtyCycle): RabByungYear {
            return RabByungYear(1024 + rabByungIndex * 60 + sixtyCycle.index)
        }

        @JvmStatic
        fun fromElementZodiac(rabByungIndex: Int, element: RabByungElement, zodiac: Zodiac): RabByungYear {
            return fromSixtyCycle(rabByungIndex, SixtyCycle(6 * (element.index * 2 + zodiac.index % 2) - 5 * zodiac.index))
        }

        @JvmStatic
        fun fromYear(year: Int): RabByungYear {
            return RabByungYear(year)
        }
    }
}

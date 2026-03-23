package com.tyme.rabbyung

import com.tyme.AbstractTyme
import com.tyme.culture.Element
import com.tyme.culture.Zodiac
import com.tyme.sixtycycle.SixtyCycle
import com.tyme.solar.SolarYear
import kotlin.jvm.JvmStatic

/**
 * 藏历年(公历1027年为藏历元年，第一饶迥火兔年）
 *
 * @author 6tail
 */
class RabByungYear(
    /** 饶迥(胜生周)序号，从0开始 */
    private var rabByungIndex: Int,
    /** 五行索引，从0开始 */
    private var elementIndex: Int,
    /** 生肖索引，从0开始 */
    private var zodiacIndex: Int
) : AbstractTyme() {
    init {
        require(rabByungIndex in 0 .. 150) { "illegal rab-byung index: $rabByungIndex" }
        require(elementIndex in 0..<Element.NAMES.size) { "illegal element index: $elementIndex" }
        require(zodiacIndex in 0..<Zodiac.NAMES.size) { "illegal zodiac index: $zodiacIndex" }
    }

    /**
     * 饶迥序号
     *
     * @return 数字，从0开始
     */
    fun getRabByungIndex(): Int {
        return rabByungIndex
    }

    /**
     * 干支
     *
     * @return 干支
     */
    fun getSixtyCycle(): SixtyCycle {
        return SixtyCycle(6 * (elementIndex * 2 + zodiacIndex % 2) - 5 * zodiacIndex)
    }

    /**
     * 生肖
     *
     * @return 生肖
     */
    fun getZodiac(): Zodiac {
        return Zodiac(zodiacIndex)
    }

    /**
     * 五行
     *
     * @return 藏历五行
     */
    fun getElement(): RabByungElement {
        return RabByungElement(elementIndex)
    }

    /**
     * 名称
     *
     * @return 名称
     */
    override fun getName(): String {
        val digits: Array<String> = arrayOf("零", "一", "二", "三", "四", "五", "六", "七", "八", "九")
        val units: Array<String> = arrayOf("", "十", "百")
        var n: Int = rabByungIndex + 1
        val s = StringBuilder()
        var pos = 0
        while (n > 0) {
            val digit: Int = n % 10
            if (digit > 0) {
                s.insert(0, digits[digit] + units[pos])
            } else if (s.isNotEmpty()) {
                s.insert(0, digits[digit])
            }
            n /= 10
            pos++
        }
        var letter: String = s.toString()
        if (letter.startsWith("一十")) {
            letter = letter.substring(1)
        }
        return "第${letter}饶迥${getElement()}${getZodiac()}年"
    }

    override fun next(n: Int): RabByungYear {
        return fromYear(getYear() + n)
    }

    /**
     * 年
     *
     * @return 年
     */
    fun getYear(): Int {
        return 1024 + rabByungIndex * 60 + getSixtyCycle().getIndex()
    }

    /**
     * 闰月
     *
     * @return 闰月数字，1代表闰1月，0代表无闰月
     */
    fun getLeapMonth(): Int {
        var y = 1
        var m = 4
        var t = 1
        val currentYear: Int = getYear()
        while (y < currentYear) {
            val i = m + 31 + t
            y += 2
            m = i - 23
            if (i > 35) {
                y += 1
                m -= 12
            }
            t = 1 - t
        }
        return if (y == currentYear) m else 0
    }

    /**
     * 公历年
     *
     * @return 公历年
     */
    fun getSolarYear(): SolarYear {
        return SolarYear(getYear())
    }

    /**
     * 首月
     *
     * @return 藏历月
     */
    fun getFirstMonth(): RabByungMonth {
        return RabByungMonth(getYear(), 1)
    }

    /**
     * 月份数量
     *
     * @return 数量
     */
    fun getMonthCount(): Int {
        return if (getLeapMonth() < 1) 12 else 13
    }

    /**
     * 藏历月列表
     *
     * @return 藏历月列表
     */
    fun getMonths(): List<RabByungMonth> {
        val l: MutableList<RabByungMonth> = ArrayList()
        val y: Int = getYear()
        val leapMonth: Int = getLeapMonth()
        for (i in 1 until 13) {
            l.add(RabByungMonth(y, i))
            if (i == leapMonth) {
                l.add(RabByungMonth(y, -i))
            }
        }
        return l
    }

    companion object {
        @JvmStatic
        fun validate(year: Int) {
            if (year !in 1027..9999) {
                throw IllegalArgumentException("illegal rab-byung year: $year")
            }
        }

        @JvmStatic
        fun fromSixtyCycle(rabByungIndex: Int, sixtyCycle: SixtyCycle): RabByungYear {
            return RabByungYear(rabByungIndex, sixtyCycle.getHeavenStem().getElement().getIndex(), sixtyCycle.getEarthBranch().getZodiac().getIndex())
        }

        @JvmStatic
        fun fromElementZodiac(rabByungIndex: Int, element: RabByungElement, zodiac: Zodiac): RabByungYear {
            return RabByungYear(rabByungIndex, element.getIndex(), zodiac.getIndex())
        }

        @JvmStatic
        fun fromYear(year: Int): RabByungYear {
            validate(year)
            return fromSixtyCycle((year - 1024) / 60, SixtyCycle(year - 4))
        }
    }
}
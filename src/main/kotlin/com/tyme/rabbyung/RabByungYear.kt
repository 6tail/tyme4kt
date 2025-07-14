package com.tyme.rabbyung

import com.tyme.AbstractTyme
import com.tyme.culture.Zodiac
import com.tyme.sixtycycle.SixtyCycle
import com.tyme.solar.SolarYear

/**
 * 藏历年(公历1027年为藏历元年，第一饶迥火兔年）
 *
 * @author 6tail
 */
class RabByungYear(
    /** 饶迥(胜生周)序号，从0开始 */
    private var rabByungIndex: Int,
    /** 干支 */
    private var sixtyCycle: SixtyCycle
) : AbstractTyme() {
    init {
        require(rabByungIndex in 0 .. 150) { "illegal rab-byung index: $rabByungIndex" }
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
        return sixtyCycle
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
        return RabByungElement(getSixtyCycle().getHeavenStem().getElement().getIndex())
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
        val s: StringBuilder = StringBuilder()
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
        var t = 0
        val currentYear: Int = getYear()
        while (y < currentYear) {
            val i: Int = m - 1 + (if (t % 2 == 0) 33 else 32)
            y = (y * 12 + i) / 12
            m = i % 12 + 1
            t++
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
        return RabByungMonth(this, 1)
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
        val leapMonth: Int = getLeapMonth()
        for (i in 1 until  13) {
            l.add(RabByungMonth(this, i))
            if (i == leapMonth) {
                l.add(RabByungMonth(this, -i))
            }
        }
        return l
    }

    companion object {
        @JvmStatic
        fun fromSixtyCycle(rabByungIndex: Int, sixtyCycle: SixtyCycle): RabByungYear {
            return RabByungYear(rabByungIndex, sixtyCycle)
        }

        @JvmStatic
        fun fromElementZodiac(rabByungIndex: Int, element: RabByungElement, zodiac: Zodiac): RabByungYear {
            for (i in 0..59) {
                val sixtyCycle = SixtyCycle(i)
                if (sixtyCycle.getEarthBranch().getZodiac().equals(zodiac) &&
                    sixtyCycle.getHeavenStem().getElement().getIndex() == element.getIndex()) {
                    return RabByungYear(rabByungIndex, sixtyCycle)
                }
            }
            throw IllegalArgumentException("illegal rab-byung element $element, zodiac $zodiac")
        }

        @JvmStatic
        fun fromYear(year: Int): RabByungYear {
            return RabByungYear((year - 1024) / 60, SixtyCycle(year - 4))
        }
    }
}
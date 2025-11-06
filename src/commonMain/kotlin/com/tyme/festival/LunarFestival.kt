package com.tyme.festival

import com.tyme.AbstractTyme
import com.tyme.enums.FestivalType
import com.tyme.lunar.LunarDay
import com.tyme.solar.SolarTerm
import com.tyme.util.pad2
import kotlin.jvm.JvmStatic

/**
 * 农历传统节日（依据国家标准《农历的编算和颁行》GB/T 33661-2017）
 *
 * @author 6tail
 */
class LunarFestival(
    /** 类型 */
    private var type: FestivalType,
    /** 农历日 */
    private var day: LunarDay,
    /** 节气 */
    private var solarTerm: SolarTerm?,
    data: String
) : AbstractTyme() {

    /** 索引 */
    private var index: Int = data.substring(1, 3).toInt(10)

    /** 名称 */
    private var name: String = NAMES[index]

    override fun getName(): String {
        return name
    }

    /**
     * 索引
     *
     * @return 索引
     */
    fun getIndex(): Int {
        return index
    }

    /**
     * 农历日
     *
     * @return 农历日
     */
    fun getDay(): LunarDay {
        return day
    }

    /**
     * 类型
     *
     * @return 节日类型
     */
    fun getType(): FestivalType {
        return type
    }

    /**
     * 节气，非节气返回null
     *
     * @return 节气
     */
    fun getSolarTerm(): SolarTerm? {
        return solarTerm
    }

    override fun toString(): String {
        return "$day $name"
    }

    override fun next(n: Int): LunarFestival? {
        val size = NAMES.size
        val i = index + n
        return fromIndex((day.getYear() * size + i) / size, indexOf(i, size))
    }

    override fun equals(other: Any?): Boolean {
        return other is LunarFestival && toString() == other.toString()
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    companion object {
        val NAMES: Array<String> = arrayOf("春节", "元宵节", "龙头节", "上巳节", "清明节", "端午节", "七夕节", "中元节", "中秋节", "重阳节", "冬至节", "腊八节", "除夕")
        var DATA: String = "@0000101@0100115@0200202@0300303@04107@0500505@0600707@0700715@0800815@0900909@10124@1101208@122"

        @JvmStatic
        fun fromIndex(year: Int, index: Int): LunarFestival? {
            require(!(index < 0 || index >= NAMES.size)) { "illegal index: $index" }
            val regex = Regex("@${index.pad2()}\\d+")
            val matchResult = regex.find(DATA)
            if (matchResult != null) {
                val data = matchResult.value
                val type: Int = data[3].code - '0'.code
                when (type) {
                    0 -> {
                        return LunarFestival(
                            FestivalType.DAY,
                            LunarDay(
                                year,
                                data.substring(4, 6).toInt(10),
                                data.substring(6).toInt(10)
                            ),
                            null,
                            data
                        )
                    }
                    1 -> {
                        val solarTerm = SolarTerm(year, data.substring(4).toInt(10))
                        return LunarFestival(
                            FestivalType.TERM,
                            solarTerm.getJulianDay()
                                .getSolarDay()
                                .getLunarDay(),
                            solarTerm,
                            data
                        )
                    }
                    2 -> {
                        return LunarFestival(
                            FestivalType.EVE,
                            LunarDay(year + 1, 1, 1).next(-1),
                            null,
                            data
                        )
                    }
                    else -> return null
                }
            }
            return null
        }

        @JvmStatic
        fun fromYmd(year: Int, month: Int, day: Int): LunarFestival? {
            var matchResult = Regex("@\\d{2}0${month.pad2()}${day.pad2()}").find(DATA)
            if (matchResult != null) {
                return LunarFestival(
                    FestivalType.DAY,
                    LunarDay(year, month, day),
                    null,
                    matchResult.value
                )
            }
            val regex = Regex("@\\d{2}1\\d{2}")
            val matches = regex.findAll(DATA)
            for (match in matches) {
                val data: String = match.value
                val solarTerm = SolarTerm(year, data.substring(4).toInt(10))
                val lunarDay = solarTerm.getSolarDay().getLunarDay()
                if (lunarDay.getYear() == year && lunarDay.getMonth() == month && lunarDay.getDay() == day) {
                    return LunarFestival(FestivalType.TERM, lunarDay, solarTerm, data)
                }
            }
            matchResult = Regex("@\\d{2}2").find(DATA)
            if (matchResult != null) {
                val lunarDay = LunarDay(year, month, day)
                val nextDay = lunarDay.next(1)
                if (nextDay.getMonth() == 1 && nextDay.getDay() == 1) {
                    return LunarFestival(
                        FestivalType.EVE,
                        lunarDay,
                        null,
                        matchResult.value
                    )
                }
            }
            return null
        }
    }
}

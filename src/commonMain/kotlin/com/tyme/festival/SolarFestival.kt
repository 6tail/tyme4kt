package com.tyme.festival

import com.tyme.AbstractTyme
import com.tyme.enums.FestivalType
import com.tyme.solar.SolarDay
import com.tyme.util.pad2
import kotlin.jvm.JvmStatic

/**
 * 公历现代节日
 *
 * @author 6tail
 */
class SolarFestival(
    /** 类型 */
    private var type: FestivalType,
    /** 公历日 */
    private var day: SolarDay,
    /** 起始年 */
    private var startYear: Int,
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
    fun getIndex(): Int{
        return index
    }

    /**
     * 公历日
     *
     * @return 公历日
     */
    fun getDay(): SolarDay {
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
     * 起始年
     *
     * @return 年
     */
    fun getStartYear(): Int {
        return startYear
    }

    override fun toString(): String {
        return "$day $name"
    }

    override fun next(n: Int): SolarFestival? {
        val size = NAMES.size
        val i = index + n
        return fromIndex((day.getYear() * size + i) / size, indexOf(i, size))
    }

    companion object {
        val NAMES: Array<String> = arrayOf("元旦", "三八妇女节", "植树节", "五一劳动节", "五四青年节", "六一儿童节", "建党节", "八一建军节", "教师节", "国庆节")
        var DATA: String = "@00001011950@01003081950@02003121979@03005011950@04005041950@05006011950@06007011941@07008011933@08009101985@09010011950"

        @JvmStatic
        fun fromIndex(year: Int, index: Int): SolarFestival? {
            require(index in NAMES.indices) { "illegal index: $index" }
            val matchResult = Regex("@${index.pad2()}\\d+").find(DATA)
            if (matchResult != null) {
                val data: String = matchResult.value
                val type: Int = data[3].code - '0'.code
                if (type == 0){
                    val startYear: Int = data.substring(8).toInt(10)
                    if (year >= startYear){
                        return SolarFestival(FestivalType.DAY, SolarDay(year, data.substring(4, 6).toInt(10), data.substring(6, 8).toInt(10)), startYear, data)
                    }
                }
            }
            return null
        }

        @JvmStatic
        fun fromYmd(year: Int, month: Int, day: Int): SolarFestival? {
            val matchResult = Regex("@\\d{2}0${month.pad2()}${day.pad2()}\\d+").find(DATA)
            if (matchResult != null) {
                val data = matchResult.value
                val startYear = data.substring(8).toInt(10)
                if (year >= startYear) {
                    return SolarFestival(FestivalType.DAY, SolarDay(year, month, day), startYear, data)
                }
            }
            return null
        }
    }
}

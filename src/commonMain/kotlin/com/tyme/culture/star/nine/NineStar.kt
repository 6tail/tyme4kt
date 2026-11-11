package com.tyme.culture.star.nine

import com.tyme.LoopTyme
import com.tyme.culture.Direction
import com.tyme.culture.Element
import com.tyme.sixtycycle.SixtyCycle
import com.tyme.solar.SolarDay
import com.tyme.solar.SolarTerm
import com.tyme.unit.AbstractTraditionalYear
import kotlin.jvm.JvmStatic

/**
 * 九星
 *
 * @author 6tail
 */
class NineStar: LoopTyme {
    constructor(index: Int): super(NAMES, index)

    constructor(name: String): super(NAMES, name)

    override fun next(n: Int): NineStar {
        return NineStar(nextIndex(n))
    }

    /**
     * 颜色
     *
     * @return 颜色
     */
    fun getColor(): String {
        return COLORS[index]
    }

    /**
     * 五行
     *
     * @return 五行
     */
    fun getElement(): Element {
        return getDirection().getElement()
    }

    /**
     * 北斗九星
     *
     * @return 北斗九星
     */
    fun getDipper(): Dipper {
        return Dipper(index)
    }

    /**
     * 方位
     *
     * @return 方位
     */
    fun getDirection(): Direction {
        return Direction(index)
    }

    override fun toString(): String {
        return getName() + getColor() + getElement().getName()
    }

    companion object {
        val NAMES: Array<String> = arrayOf("一", "二", "三", "四", "五", "六", "七", "八", "九")
        val COLORS: Array<String> = arrayOf("白", "黑", "碧", "绿", "黄", "白", "赤", "白", "紫")

        @JvmStatic
        fun fromYm(year: AbstractTraditionalYear, month: SixtyCycle): NineStar {
            var index: Int = month.getEarthBranch().index
            if (index < 2) {
                index += 3
            }
            return NineStar(27 - year.getSixtyCycle().getEarthBranch().index % 3 * 3 - index)
        }

        @JvmStatic
        fun fromHour(solarDay: SolarDay, daySixtyCycle: SixtyCycle, hourIndexInDay: Int): NineStar {
            val dongZhi = SolarTerm(solarDay.year, 0)
            val earthBranchIndex = hourIndexInDay % 12
            var index: Int = 8 - 3 * (daySixtyCycle.getEarthBranch().index % 3)
            if (!solarDay.isBefore(dongZhi.getJulianDay().getSolarDay()) && solarDay.isBefore(dongZhi.next(12).getJulianDay().getSolarDay())) {
                index = 8 + earthBranchIndex - index
            } else {
                index -= earthBranchIndex
            }
            return NineStar(index)
        }

        @JvmStatic
        fun fromIndex(index: Int): NineStar {
            return NineStar(index)
        }

        @JvmStatic
        fun fromName(name: String): NineStar {
            return NineStar(name)
        }
    }
}

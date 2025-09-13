package com.tyme.culture.fetus

import com.tyme.AbstractCulture
import com.tyme.culture.Direction
import com.tyme.enums.Side
import com.tyme.lunar.LunarDay
import com.tyme.sixtycycle.SixtyCycle
import com.tyme.sixtycycle.SixtyCycleDay

/**
 * 逐日胎神
 *
 * @author 6tail
 */
class FetusDay(sixtyCycle: SixtyCycle) : AbstractCulture() {
    /** 天干六甲胎神 */
    private var fetusHeavenStem: FetusHeavenStem = FetusHeavenStem(sixtyCycle.getHeavenStem().getIndex() % 5)

    /** 地支六甲胎神 */
    private var fetusEarthBranch: FetusEarthBranch = FetusEarthBranch(sixtyCycle.getEarthBranch().getIndex() % 6)

    /** 内外 */
    private var side: Side

    /** 方位 */
    private var direction: Direction

    init {
        val index = intArrayOf(3, 3, 8, 8, 8, 8, 8, 1, 1, 1, 1, 1, 1, 6, 6, 6, 6, 6, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, -9, -9, -9, -9, -9, -5, -5, -1, -1, -1, -3, -7, -7, -7, -7, -5, 7, 7, 7, 7, 7, 7, 2, 2, 2, 2, 2, 3, 3, 3, 3)[sixtyCycle.getIndex()]
        side = if (index < 0) Side.IN else Side.OUT
        direction = Direction(index)
    }

    override fun getName(): String {
        var s: String = fetusHeavenStem.getName() + fetusEarthBranch.getName()
        if ("门门" == s) {
            s = "占大门"
        } else if ("碓磨碓" == s) {
            s = "占碓磨"
        } else if ("房床床" == s) {
            s = "占房床"
        } else if (s.startsWith("门")) {
            s = "占$s"
        }
        s += " "
        s += if (Side.IN == side) "房内" else "外"

        val directionName: String = direction.getName()
        if (Side.OUT == side && "北南西东".contains(directionName)) {
            s += "正"
        }
        s += directionName
        return s
    }

    fun getSide(): Side {
        return side
    }

    fun getDirection(): Direction {
        return direction
    }

    fun getFetusHeavenStem(): FetusHeavenStem {
        return fetusHeavenStem
    }

    fun getFetusEarthBranch(): FetusEarthBranch {
        return fetusEarthBranch
    }

    companion object {
        /**
         * 从农历日初始化
         *
         * @param lunarDay 农历日
         * @return 逐日胎神
         */
        @JvmStatic
        fun fromLunarDay(lunarDay: LunarDay): FetusDay {
            return FetusDay(lunarDay.getSixtyCycle())
        }

        /**
         * 从干支日初始化
         *
         * @param sixtyCycleDay 干支日
         * @return 逐日胎神
         */
        @JvmStatic
        fun fromSixtyCycleDay(sixtyCycleDay: SixtyCycleDay): FetusDay {
            return FetusDay(sixtyCycleDay.getSixtyCycle())
        }
    }
}

package com.tyme.eightchar

import com.tyme.eightchar.provider.ChildLimitProvider
import com.tyme.eightchar.provider.impl.DefaultChildLimitProvider
import com.tyme.enums.Gender
import com.tyme.enums.YinYang
import com.tyme.sixtycycle.SixtyCycleYear
import com.tyme.solar.SolarTerm
import com.tyme.solar.SolarTime

/**
 * 童限（从出生到起运的时间段）
 *
 * @author 6tail
 */
class ChildLimit (birthTime: SolarTime, private var gender: Gender) {
    /** 八字 */
    private var eightChar: EightChar = birthTime.getLunarHour().getEightChar()

    /** 顺逆 */
    private var forward: Boolean
    /** 童限信息 */
    private var info: ChildLimitInfo

    init {
        val yang = YinYang.YANG == eightChar.getYear().getHeavenStem().getYinYang()
        val man: Boolean = Gender.MAN == gender
        forward = (yang && man) || (!yang && !man)
        var term: SolarTerm = birthTime.getTerm()
        if (!term.isJie()) {
            term = term.next(-1)
        }
        if (forward) {
            term = term.next(2)
        }
        info = provider.getInfo(birthTime, term)
    }

    /**
     * 获取八字
     *
     * @return 八字 eightChar
     */
    fun getEightChar(): EightChar {
        return eightChar
    }

    /**
     * 获取性别
     *
     * @return 性别 Gender
     */
    fun getGender(): Gender {
        return gender
    }

    /**
     * 年数
     *
     * @return 年数
     */
    fun getYearCount(): Int {
        return info.getYearCount()
    }

    /**
     * 月数
     *
     * @return 月数
     */
    fun getMonthCount(): Int {
        return info.getMonthCount()
    }

    /**
     * 日数
     *
     * @return 日数
     */
    fun getDayCount(): Int {
        return info.getDayCount()
    }

    /**
     * 小时数
     *
     * @return 小时数
     */
    fun getHourCount(): Int {
        return info.getHourCount()
    }

    /**
     * 分钟数
     *
     * @return 分钟数
     */
    fun getMinuteCount(): Int {
        return info.getMinuteCount()
    }

    /**
     * 开始(即出生)的公历时刻
     *
     * @return 公历时刻
     */
    fun getStartTime(): SolarTime {
        return info.getStartTime()
    }

    /**
     * 结束(即开始起运)的公历时刻
     *
     * @return 公历时刻
     */
    fun getEndTime(): SolarTime {
        return info.getEndTime()
    }

    /**
     * 是否顺推
     * @return true/false
     */
    fun isForward(): Boolean {
        return forward
    }

    /**
     * 起运大运
     *
     * @return 大运
     */
    fun getStartDecadeFortune(): DecadeFortune {
        return DecadeFortune(this, 0)
    }

    /**
     * 所属大运
     *
     * @return 大运
     */
    fun getDecadeFortune(): DecadeFortune {
        return DecadeFortune(this, -1)
    }

    /**
     * 小运
     *
     * @return 小运
     */
    fun getStartFortune(): Fortune {
        return Fortune(this, 0)
    }

    /**
     * 开始(即出生)干支年
     *
     * @return 干支年
     */
    fun getStartSixtyCycleYear(): SixtyCycleYear {
        return SixtyCycleYear(getStartTime().getYear())
    }

    /**
     * 结束(即起运)干支年
     *
     * @return 干支年
     */
    fun getEndSixtyCycleYear(): SixtyCycleYear {
        return SixtyCycleYear(getEndTime().getYear())
    }

    /**
     * 开始年龄
     *
     * @return 开始年龄
     */
    fun getStartAge(): Int {
        return 1
    }

    /**
     * 结束年龄
     *
     * @return 结束年龄
     */
    fun getEndAge(): Int {
        val n: Int = getEndSixtyCycleYear().getYear() - getStartSixtyCycleYear().getYear()
        return n.coerceAtLeast(1)
    }

    companion object {
        /** 童限计算接口 */
        var provider: ChildLimitProvider = DefaultChildLimitProvider()

        @JvmStatic
        fun fromSolarTime(birthTime: SolarTime, gender: Gender): ChildLimit {
            return ChildLimit(birthTime, gender)
        }
    }
}

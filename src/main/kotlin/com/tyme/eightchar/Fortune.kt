package com.tyme.eightchar

import com.tyme.AbstractTyme
import com.tyme.sixtycycle.SixtyCycle
import com.tyme.sixtycycle.SixtyCycleYear

/**
 * 小运
 * 在十年大运中，每一年为一小运。童限结束的公历时刻，既是大运的开始，也是小运的开始。
 * @author 6tail
 */
class Fortune(
    /** 童限 */
    protected var childLimit: ChildLimit,
    /** 序号 */
    protected var index: Int
) : AbstractTyme() {

    /**
     * 年龄
     *
     * @return 年龄
     */
    fun getAge(): Int {
        return childLimit.getEndSixtyCycleYear().getYear() - childLimit.getStartSixtyCycleYear().getYear() + 1 + index
    }

    /**
     * 获取干支年
     * 由于1大运为10年，对应10小运，因此1小运对应1年，称为流年，但注意小运干支并不等于流年干支。
     * @returns
     */
    fun getSixtyCycleYear(): SixtyCycleYear {
        return childLimit.getEndSixtyCycleYear().next(index)
    }

    /**
     * 干支
     *
     * @return 干支
     */
    fun getSixtyCycle(): SixtyCycle {
        val n: Int = getAge()
        return childLimit.getEightChar().getHour().next(if (childLimit.isForward()) n else -n)
    }

    override fun getName(): String {
        return getSixtyCycle().getName()
    }

    override fun next(n: Int): Fortune {
        return Fortune(childLimit, index + n)
    }

    companion object {
        @JvmStatic
        fun fromChildLimit(childLimit: ChildLimit, index: Int): Fortune {
            return Fortune(childLimit, index)
        }
    }
}

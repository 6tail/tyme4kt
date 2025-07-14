package com.tyme.eightchar

import com.tyme.AbstractTyme
import com.tyme.sixtycycle.SixtyCycle
import com.tyme.sixtycycle.SixtyCycleYear

/**
 * 大运（10年1大运）
 *
 * @author 6tail
 */
class DecadeFortune(
    /** 童限 */
    protected var childLimit: ChildLimit,
    /** 序号 */
    protected var index: Int
) : AbstractTyme() {

    /**
     * 开始年龄
     *
     * @return 开始年龄
     */
    fun getStartAge(): Int {
        return childLimit.getEndSixtyCycleYear().getYear() - childLimit.getStartSixtyCycleYear().getYear() + 1 + index * 10
    }

    /**
     * 结束年龄
     *
     * @return 结束年龄
     */
    fun getEndAge(): Int {
        return getStartAge() + 9
    }

    /**
     * 开始干支年
     *
     * @return 干支年
     */
    fun getStartSixtyCycleYear(): SixtyCycleYear {
        return childLimit.getEndSixtyCycleYear().next(index * 10)
    }

    /**
     * 结束干支年
     *
     * @return 干支年
     */
    fun getEndSixtyCycleYear(): SixtyCycleYear {
        return getStartSixtyCycleYear().next(9)
    }

    /**
     * 干支
     *
     * @return 干支
     */
    fun getSixtyCycle(): SixtyCycle {
        return childLimit.getEightChar().getMonth().next(if (childLimit.isForward()) index + 1 else -index - 1)
    }

    override fun getName(): String {
        return getSixtyCycle().getName()
    }

    override fun next(n: Int): DecadeFortune {
        return DecadeFortune(childLimit, index + n)
    }

    /**
     * 本轮大运中开始的小运 Fortune
     * @return 小运
     */
    fun getStartFortune(): Fortune {
        return Fortune(childLimit, index * 10)
    }

    companion object {
        @JvmStatic
        fun fromChildLimit(childLimit: ChildLimit, index: Int): DecadeFortune {
            return DecadeFortune(childLimit, index)
        }
    }
}

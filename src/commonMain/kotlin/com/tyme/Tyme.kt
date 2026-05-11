package com.tyme

/**
 * Tyme
 *
 * @author 6tail
 */
interface Tyme : Culture {
    /**
     * 推移
     *
     * @param n 推移步数，正数顺推，负数逆推
     * @return 推移后的Tyme
     */
    fun next(n: Int): Tyme?
}

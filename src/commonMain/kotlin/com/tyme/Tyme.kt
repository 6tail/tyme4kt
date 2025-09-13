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
     * @param n 推移步数
     * @return 推移后的Tyme
     */
    fun next(n: Int): Tyme?
}

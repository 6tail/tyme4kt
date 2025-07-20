package com.tyme

/**
 * 抽象Tyme
 *
 * @author 6tail
 */
abstract class AbstractTyme : AbstractCulture(), Tyme {
    abstract override fun next(n: Int): Tyme?
}

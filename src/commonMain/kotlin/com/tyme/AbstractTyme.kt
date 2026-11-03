package com.tyme

/**
 * 抽象Tyme
 *
 * @author 6tail
 */
abstract class AbstractTyme: AbstractCulture(), Tyme {
    override fun next(n: Int): Tyme? {
        throw UnsupportedOperationException()
    }
}

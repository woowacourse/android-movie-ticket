package woowacourse.movie.domain.model.discount.policy

import woowacourse.movie.domain.model.discount.discountable.Discountable

abstract class DiscountPolicy(private val discountable: Discountable) {
    abstract fun discount(money: Int): Int

    protected fun isDiscountable(): Boolean = discountable.isDiscountable()
}

package woowacourse.movie.domain.discount.discountpolicy

import woowacourse.movie.domain.model.Money

abstract class AmountDiscountPolicy(private val amount: Int) : DiscountPolicy {

    override fun discount(price: Money): Money {
        if (isDiscount()) return price.reduceMoneyWithAmount(amount)
        return price
    }

    abstract fun isDiscount(): Boolean
}

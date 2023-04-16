package woowacourse.movie.domain.discount.discountpolicy

import woowacourse.movie.domain.model.Money

abstract class RateDiscountPolicy(private val rate: Double) : DiscountPolicy {

    override fun discount(price: Money): Money {
        if (isDiscount()) return price.reduceMoneyWithRate(rate)
        return price
    }

    abstract fun isDiscount(): Boolean
}

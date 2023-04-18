package woowacourse.movie.discount.discountpolicy

import woowacourse.movie.discount.discountcondition.DiscountCondition
import woowacourse.movie.model.Money

class RateDiscountPolicy(
    conditions: List<DiscountCondition>,
    private val rate: Double,
) : DiscountPolicy(conditions) {

    override fun calculateDiscountMoney(price: Money): Money {
        return price - (price * rate)
    }
}

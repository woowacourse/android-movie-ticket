package woowacourse.movie.domain.discount.discountpolicy

import woowacourse.movie.domain.discount.discountcondition.DiscountCondition
import woowacourse.movie.domain.model.Money

class RateDiscountPolicy(
    conditions: List<DiscountCondition>,
    private val rate: Double,
) : DiscountPolicy(conditions) {

    override fun calculateDiscountMoney(price: Money): Money {
        return price - (price * rate)
    }
}

package woowacourse.movie.domain.model.discount.discountpolicy

import woowacourse.movie.domain.model.discount.discountcondition.DiscountCondition
import woowacourse.movie.domain.model.tools.Money

class RateDiscountPolicy(
    conditions: List<DiscountCondition>,
    private val rate: Double,
) : DiscountPolicy(conditions) {

    override fun calculateDiscountMoney(price: Money): Money {
        return price - (price * rate)
    }
}

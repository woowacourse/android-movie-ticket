package woowacourse.movie.domain.model.discount.discountpolicy

import woowacourse.movie.domain.model.discount.discountcondition.DiscountCondition
import woowacourse.movie.domain.model.tools.Money

class AmountDiscountPolicy(
    conditions: List<DiscountCondition>,
    private val amount: Int,
) : DiscountPolicy(conditions) {

    override fun calculateDiscountMoney(price: Money): Money = price - amount
}

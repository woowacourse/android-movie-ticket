package woowacourse.movie.discount.discountpolicy

import woowacourse.movie.discount.discountcondition.DiscountCondition
import woowacourse.movie.model.Money

class AmountDiscountPolicy(
    conditions: List<DiscountCondition>,
    private val amount: Int,
) : DiscountPolicy(conditions) {

    override fun calculateDiscountMoney(price: Money): Money = price - amount
}

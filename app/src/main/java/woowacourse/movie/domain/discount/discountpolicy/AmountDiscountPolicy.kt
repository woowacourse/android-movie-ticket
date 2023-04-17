package woowacourse.movie.domain.discount.discountpolicy

import woowacourse.movie.domain.discount.discountcondition.DiscountCondition
import woowacourse.movie.domain.model.Money

class AmountDiscountPolicy(
    conditions: List<DiscountCondition>,
    private val amount: Int,
) : DiscountPolicy(conditions) {

    override fun calculateDiscountMoney(price: Money): Money = price.reduceMoneyWithAmount(amount)
}

package woowacourse.movie.domain.model.discount.discountpolicy

import woowacourse.movie.domain.model.discount.discountcondition.DiscountCondition
import woowacourse.movie.domain.model.tools.Money
import java.time.LocalDateTime

abstract class DiscountPolicy(
    private val conditions: List<DiscountCondition>,
) {
    fun getDiscountMoney(price: Money, dateTime: LocalDateTime): Money {
        val isDiscount = conditions.any { it.isDiscount(dateTime) }
        if (isDiscount) return calculateDiscountMoney(price)
        return price
    }

    abstract fun calculateDiscountMoney(price: Money): Money
}

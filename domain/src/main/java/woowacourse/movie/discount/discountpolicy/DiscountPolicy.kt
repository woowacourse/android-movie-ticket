package woowacourse.movie.discount.discountpolicy

import woowacourse.movie.discount.discountcondition.DiscountCondition
import woowacourse.movie.model.Money
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

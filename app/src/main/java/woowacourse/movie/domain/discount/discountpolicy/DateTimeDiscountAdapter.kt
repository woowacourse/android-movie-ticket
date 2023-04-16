package woowacourse.movie.domain.discount.discountpolicy

import woowacourse.movie.domain.model.Money
import java.time.LocalDateTime

class DateTimeDiscountAdapter(
    override val dateTime: LocalDateTime,
) : DateTimeDiscountPolicy {

    private val policies = listOf(
        MovieDayTimeDiscountPolicy(dateTime, DISCOUNT_RATE),
        EarlyBirdTimeDiscountPolicy(dateTime, DISCOUNT_AMOUNT),
        LateNightTimeDiscountPolicy(dateTime, DISCOUNT_AMOUNT),
    )

    override fun discount(price: Money): Money {
        var discountedMoney = price

        policies.forEach { discountedMoney = it.discount(discountedMoney) }
        return discountedMoney
    }

    companion object {
        private const val DISCOUNT_RATE = 0.1
        private const val DISCOUNT_AMOUNT = 2000
    }
}

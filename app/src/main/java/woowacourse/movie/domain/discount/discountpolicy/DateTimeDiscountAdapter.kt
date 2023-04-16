package woowacourse.movie.domain.discount.discountpolicy

import woowacourse.movie.domain.model.Money
import java.time.LocalDateTime

class DateTimeDiscountAdapter(
    override val dateTime: LocalDateTime,
) : DateTimeDiscountPolicy {

    val policies = listOf(
        MovieDayTimeDiscountPolicy(dateTime, 0.1),
        EarlyBirdTimeDiscountPolicy(dateTime, 2000),
        LateNightTimeDiscountPolicy(dateTime, 2000),
    )

    override fun discount(price: Money): Money {
        var discountedMoney = price

        policies.forEach { discountedMoney = it.discount(discountedMoney) }
        return discountedMoney
    }
}

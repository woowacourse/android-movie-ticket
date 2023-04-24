package woowacourse.movie.domain.model.discount.discountpolicy

import woowacourse.movie.domain.model.tools.Money
import java.time.LocalDateTime

class DiscountPolicyAdapter(private val policies: List<DiscountPolicy>) {
    fun discount(price: Money, dateTime: LocalDateTime): Money {
        var discountedMoney = price
        policies.forEach { discountedMoney = it.getDiscountMoney(discountedMoney, dateTime) }
        return discountedMoney
    }
}

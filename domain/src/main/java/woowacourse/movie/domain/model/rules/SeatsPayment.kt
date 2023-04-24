package woowacourse.movie.domain.model.rules

import woowacourse.movie.domain.model.discount.MovieDiscountPolicy
import woowacourse.movie.domain.model.discount.discountpolicy.DiscountPolicyAdapter
import woowacourse.movie.domain.model.tools.Money
import woowacourse.movie.domain.model.tools.seat.Seats
import java.time.LocalDateTime

class SeatsPayment(private val seats: Seats) {

    fun getDiscountedMoneyByDateTime(dateTime: LocalDateTime): Money {
        val discountPolicy = DiscountPolicyAdapter(MovieDiscountPolicy.policies)
        val paymentMoneyAmount =
            seats.value.sumOf { seat ->
                getDiscountedMoney(seat.getPrice(), discountPolicy, dateTime).value
            }
        return Money(paymentMoneyAmount)
    }

    private fun getDiscountedMoney(
        money: Money,
        discountPolicy: DiscountPolicyAdapter,
        dateTime: LocalDateTime,
    ): Money {
        return discountPolicy.discount(money, dateTime)
    }
}

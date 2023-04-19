package woowacourse.movie.model

import woowacourse.movie.discount.MovieDiscountPolicy
import woowacourse.movie.discount.discountpolicy.DiscountPolicyAdapter
import java.time.LocalDateTime

data class Ticket(
    val movieId: Long,
    val bookedDateTime: LocalDateTime,
    val count: Int,
    val seats: List<Seat>,
) {
    fun getPaymentMoney(): Money {
        val discountPolicy = DiscountPolicyAdapter(MovieDiscountPolicy.policies)
        val paymentMoneyAmount =
            seats.sumOf { seat ->
                val ticketPrice = SeatGrade.from(seat).ticketPrice
                getDiscountedMoney(ticketPrice, discountPolicy).value
            }
        return Money(paymentMoneyAmount)
    }

    private fun getDiscountedMoney(money: Money, discountPolicy: DiscountPolicyAdapter) =
        discountPolicy.discount(money, bookedDateTime)
}

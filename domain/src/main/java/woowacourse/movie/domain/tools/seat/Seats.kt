package woowacourse.movie.domain.tools.seat

import woowacourse.movie.domain.discount.MovieDiscountPolicy
import woowacourse.movie.domain.discount.discountpolicy.DiscountPolicyAdapter
import woowacourse.movie.domain.tools.Money
import java.time.LocalDateTime

class Seats(seats: List<Seat> = listOf()) {

    private val _value = seats.toMutableList()

    val value
        get() = _value.toList()

    val size
        get() = value.size

    fun addSeat(seat: Seat) {
        _value.add(seat)
        println(_value)
    }

    fun removeSeat(seat: Seat) {
        _value.remove(seat)
        println(_value)
    }

    fun contains(seat: Seat): Boolean = value.contains(seat)

    fun sorted(): Seats {
        val sortedValue = value.sortedWith(
            compareBy(
                { it.location.row },
                { it.location.number },
            ),
        )
        return Seats(sortedValue)
    }

    fun getPaymentMoney(dateTime: LocalDateTime): Money {
        val discountPolicy = DiscountPolicyAdapter(MovieDiscountPolicy.policies)
        val paymentMoneyAmount =
            value.sumOf { seat ->
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

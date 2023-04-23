package woowacourse.movie.domain.tools.seat

import woowacourse.movie.domain.discount.MovieDiscountPolicy
import woowacourse.movie.domain.discount.discountpolicy.DiscountPolicyAdapter
import woowacourse.movie.domain.tools.Money
import java.time.LocalDateTime
import java.util.SortedSet

class Seats(seats: SortedSet<Seat> = sortedSetOf()) {

    private val _value = seats.toMutableSet()

    val value
        get() = _value.toSortedSet()

    val size
        get() = value.size

    fun addSeat(seat: Seat) {
        _value.add(seat)
    }

    fun removeSeat(seat: Seat) {
        _value.remove(seat)
    }

    fun contains(seat: Seat): Boolean = value.contains(seat)

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

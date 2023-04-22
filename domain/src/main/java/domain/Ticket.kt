package domain

import domain.discountPolicy.DisCountPolicies
import domain.discountPolicy.DiscountPolicy
import domain.seatPolicy.SeatPolicies
import java.time.LocalDateTime

data class Ticket(
    val date: LocalDateTime,
    val seat: Seat,
    val disCountPolicies: DisCountPolicies
) {
    val discountPrice: Price
        get() = disCountPolicies.calculate(this, seat.rank.price)
}

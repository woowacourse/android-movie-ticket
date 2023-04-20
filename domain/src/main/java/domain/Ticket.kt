package domain

import domain.discountPolicy.DisCountPolicies
import domain.discountPolicy.DiscountPolicy
import domain.seatPolicy.SeatPolicies
import java.time.LocalDateTime

data class Ticket(
    val date: LocalDateTime,
    val seat: Seat,
) {
    fun getDiscountPrice(disCountPolicies: DisCountPolicies, seatPolicies: SeatPolicies): Price =
        disCountPolicies.calculate(this, getSeatPrice(seatPolicies))
    fun getSeatPrice(seatPolicies: SeatPolicies): Price = seat.getPrice(seatPolicies)
}

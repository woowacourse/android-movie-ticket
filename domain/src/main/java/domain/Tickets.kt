package domain

import domain.discountPolicy.DisCountPolicies
import domain.seatPolicy.SeatPolicies

class Tickets(val list: List<Ticket>) {
    fun getTotalPrice(disCountPolicies: DisCountPolicies, seatPolicies: SeatPolicies): Price {
        return Price(list.sumOf { it.getDiscountPrice(disCountPolicies, seatPolicies).value })
    }
}

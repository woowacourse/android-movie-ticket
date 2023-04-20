package domain.seatPolicy

import domain.Price
import domain.Seat

class SeatPolicies(private val seatPolicies: List<SeatPolicy>) {
    fun getPrice(seat: Seat): Price {
        for (policy in seatPolicies) {
            if (policy.checkCondition(seat)) return policy.price
        }
        return Price(DEFAULT_PRICE)
    }

    companion object {
        private const val DEFAULT_PRICE = 10_000
    }
}

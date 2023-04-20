package domain.seatPolicy

import domain.Price
import domain.Seat

class RankAPolicy(
    private val startRowCondition: Int = DEFAULT_START_CONDITION,
    override val price: Price = Price(DEFAULT_PRICE)
) : SeatPolicy {
    override fun checkCondition(seat: Seat): Boolean =
        (seat.row >= startRowCondition)

    companion object {
        private const val DEFAULT_START_CONDITION = 5
        private const val DEFAULT_PRICE = 12_000
    }
}
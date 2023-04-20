package domain.seatPolicy

import domain.Price
import domain.Seat

class RankBPolicy (
    private val startRowCondition: Int = DEFAULT_START_CONDITION,
    private val EndRowCondition: Int = DEFAULT_END_CONDITION,
    override val price: Price = Price(DEFAULT_PRICE)
) : SeatPolicy {
    override fun checkCondition(seat: Seat): Boolean =
        (seat.row in startRowCondition..EndRowCondition)

    companion object {
        private const val DEFAULT_START_CONDITION = 1
        private const val DEFAULT_END_CONDITION = 2
        const val DEFAULT_PRICE = 10_000
    }
}
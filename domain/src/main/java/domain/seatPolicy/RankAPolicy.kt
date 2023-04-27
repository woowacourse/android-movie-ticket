package domain.seatPolicy

import domain.Price
import domain.Seat

class RankAPolicy(
    private val startRowCondition: Int = DEFAULT_START_CONDITION,
) : SeatPolicy {
    override val seatRank: SeatRank = SeatRank.A
    override fun checkCondition(seat: Seat): Boolean =
        (seat.row >= startRowCondition)

    companion object {
        private const val DEFAULT_START_CONDITION = 5
    }
}

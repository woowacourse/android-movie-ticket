package domain.seatPolicy

import domain.Price
import domain.Seat

class RankSPolicy(
    private val startRowCondition: Int = DEFAULT_START_CONDITION,
    private val EndRowCondition: Int = DEFAULT_END_CONDITION,
) : SeatPolicy {
    override val seatRank: SeatRank = SeatRank.S
    override fun checkCondition(seat: Seat): Boolean =
        (seat.row in startRowCondition..EndRowCondition)

    companion object {
        private const val DEFAULT_START_CONDITION = 3
        private const val DEFAULT_END_CONDITION = 4
    }
}

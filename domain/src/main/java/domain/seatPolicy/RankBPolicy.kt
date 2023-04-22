package domain.seatPolicy

import domain.Seat

class RankBPolicy (
    private val startRowCondition: Int = DEFAULT_START_CONDITION,
    private val EndRowCondition: Int = DEFAULT_END_CONDITION,
) : SeatPolicy {
    override val seatRank: SeatRank = SeatRank.B
    override fun checkCondition(seat: Seat): Boolean =
        (seat.row in startRowCondition..EndRowCondition)

    companion object {
        private const val DEFAULT_START_CONDITION = 1
        private const val DEFAULT_END_CONDITION = 2
    }
}

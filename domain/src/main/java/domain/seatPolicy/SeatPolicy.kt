package domain.seatPolicy

import domain.Price
import domain.Seat

interface SeatPolicy {
    val seatRank: SeatRank
    fun checkCondition(seat: Seat): Boolean
}

package domain.seatPolicy

import domain.Price
import domain.Seat

interface SeatPolicy {
    val price: Price
    fun checkCondition(seat: Seat): Boolean
}
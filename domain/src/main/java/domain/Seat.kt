package domain

import domain.seatPolicy.SeatPolicies
import domain.seatPolicy.SeatRank

data class Seat(val row: Int, val col: Int, val seatPolicies: SeatPolicies) {
    val rank: SeatRank
        get() = seatPolicies.getSeatRank(this)
}

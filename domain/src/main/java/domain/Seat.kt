package domain

import domain.seatPolicy.SeatRank

data class Seat(val row: Int, val col: Int) {
    val rank: SeatRank
        get() = SeatRank.getRank(row = row)
}

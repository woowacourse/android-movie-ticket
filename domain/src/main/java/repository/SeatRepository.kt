package repository

import data.SeatPosition
import data.SeatRank

object SeatRepository {
    fun getPriceOf(seatRank: SeatRank): Int = when (seatRank) {
        SeatRank.SRank -> 15_000
        SeatRank.ARank -> 12_000
        SeatRank.BRank -> 10_000
    }

    val seats = mapOf(
        SeatPosition(0, 0) to SeatRank.BRank,
        SeatPosition(0, 1) to SeatRank.BRank,
        SeatPosition(0, 2) to SeatRank.BRank,
        SeatPosition(0, 3) to SeatRank.BRank,
        SeatPosition(1, 0) to SeatRank.BRank,
        SeatPosition(1, 1) to SeatRank.BRank,
        SeatPosition(1, 2) to SeatRank.BRank,
        SeatPosition(1, 3) to SeatRank.BRank,
        SeatPosition(2, 0) to SeatRank.SRank,
        SeatPosition(2, 1) to SeatRank.SRank,
        SeatPosition(2, 2) to SeatRank.SRank,
        SeatPosition(2, 3) to SeatRank.SRank,
        SeatPosition(3, 0) to SeatRank.SRank,
        SeatPosition(3, 1) to SeatRank.SRank,
        SeatPosition(3, 2) to SeatRank.SRank,
        SeatPosition(3, 3) to SeatRank.SRank,
        SeatPosition(4, 0) to SeatRank.ARank,
        SeatPosition(4, 1) to SeatRank.ARank,
        SeatPosition(4, 2) to SeatRank.ARank,
        SeatPosition(4, 3) to SeatRank.ARank,
    )
}

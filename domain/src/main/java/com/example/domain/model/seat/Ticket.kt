package com.example.domain.model.seat

import com.example.domain.model.Money

data class Ticket(
    val position: SeatPosition
) {
    val rank: SeatRank
        get() = when (position.row) {
            SeatRow.A, SeatRow.B -> SeatRank.B
            SeatRow.C, SeatRow.D -> SeatRank.S
            SeatRow.E -> SeatRank.A
        }

    val money: Money
        get() = rank.money
}

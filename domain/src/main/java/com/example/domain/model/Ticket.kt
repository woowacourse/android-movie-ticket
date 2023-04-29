package com.example.domain.model

import com.example.domain.model.seat.SeatPosition
import com.example.domain.model.seat.SeatRank
import java.time.LocalDateTime

data class Ticket(
    val movie: Movie,
    val dateTime: LocalDateTime,
    val position: SeatPosition,
    val discountedMoney: Money
) {
    val rank: SeatRank
        get() = SeatRank.from(position)

    val originMoney: Money
        get() = rank.money
}

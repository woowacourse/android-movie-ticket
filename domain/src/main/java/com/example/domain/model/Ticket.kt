package com.example.domain.model

import com.example.domain.discountPolicy.DiscountPolicy
import com.example.domain.model.seat.SeatPosition
import com.example.domain.model.seat.SeatRank
import com.example.domain.model.seat.SeatRow
import java.time.LocalDateTime

data class Ticket(
    val movie: Movie,
    val dateTime: LocalDateTime,
    val position: SeatPosition
) {
    val rank: SeatRank
        get() = when (position.row) {
            SeatRow.A, SeatRow.B -> SeatRank.B
            SeatRow.C, SeatRow.D -> SeatRank.S
            SeatRow.E -> SeatRank.A
        }

    val originMoney: Money
        get() = rank.money

    fun getDiscountMoney(discountPolicy: DiscountPolicy): Money = discountPolicy.discount(this)
}

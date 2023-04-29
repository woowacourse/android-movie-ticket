package com.example.domain.usecase

import com.example.domain.model.Money
import com.example.domain.model.Movie
import com.example.domain.model.seat.SeatPosition
import com.example.domain.ticketSeller.TicketSeller
import java.time.LocalDateTime

class DiscountApplyUseCase(private val ticketSeller: TicketSeller = TicketSeller()) {
    operator fun invoke(movie: Movie, dateTime: LocalDateTime, seats: List<SeatPosition>): Money {
        return ticketSeller.predictMoney(movie, dateTime, seats)
    }
}

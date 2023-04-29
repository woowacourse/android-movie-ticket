package com.example.domain.ticketSeller

import com.example.domain.discountPolicy.DefaultDiscountPolicy
import com.example.domain.discountPolicy.DiscountPolicy
import com.example.domain.model.Money
import com.example.domain.model.Movie
import com.example.domain.model.Ticket
import com.example.domain.model.Tickets
import com.example.domain.model.seat.SeatPosition
import java.time.LocalDateTime

class TicketSeller(val discountPolicy: DiscountPolicy = DefaultDiscountPolicy()) {

    fun predictMoney(movie: Movie, dateTime: LocalDateTime, seats: List<SeatPosition>): Money {
        return Money(seats.sumOf { discountPolicy.discount(movie, dateTime, it).value })
    }

    fun issueTickets(movie: Movie, dateTime: LocalDateTime, seats: List<SeatPosition>): Tickets {
        return Tickets(seats.map { issueTicket(movie, dateTime, it) })
    }

    private fun issueTicket(
        movie: Movie,
        dateTime: LocalDateTime,
        seatPosition: SeatPosition
    ): Ticket {
        val discountedMoney = discountPolicy.discount(movie, dateTime, seatPosition)
        return Ticket(movie, dateTime, seatPosition, discountedMoney)
    }
}

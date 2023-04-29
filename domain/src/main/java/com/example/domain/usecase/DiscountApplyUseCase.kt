package com.example.domain.usecase

import com.example.domain.discountPolicy.DefaultDiscountPolicy
import com.example.domain.discountPolicy.DiscountPolicy
import com.example.domain.model.Money
import com.example.domain.model.Movie
import com.example.domain.model.Ticket
import com.example.domain.model.seat.SeatPosition
import java.time.LocalDateTime

class DiscountApplyUseCase(private val discountPolicy: DiscountPolicy = DefaultDiscountPolicy()) {
    operator fun invoke(movie: Movie, dateTime: LocalDateTime, seats: List<SeatPosition>): Money {
        val tickets = seats.map { Ticket(movie, dateTime, it) }
        return getTotalDiscountApplyMoney(tickets)
    }

    private fun getTotalDiscountApplyMoney(tickets: List<Ticket>): Money =
        Money(tickets.sumOf { it.getDiscountMoney(discountPolicy).value })
}

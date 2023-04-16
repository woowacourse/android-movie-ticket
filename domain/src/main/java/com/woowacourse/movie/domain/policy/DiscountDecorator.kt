package com.woowacourse.movie.domain.policy

import com.woowacourse.movie.domain.Reservation
import java.time.LocalDate
import java.time.LocalTime

class DiscountDecorator(movieDate: LocalDate, movieTime: LocalTime) {
    private val policies = listOf(
        MovieDayPolicy(movieDate),
        EarlyAndLatePolicy(movieTime)
    )

    fun calculatePrice(reservation: Reservation): Int {
        var money = DEFAULT_TICKET_PRICE
        policies.forEach {
            money = it.calculatePrice(money)
        }
        return money * reservation.ticket.count
    }

    companion object {
        private const val DEFAULT_TICKET_PRICE = 13_000
    }
}

package com.woowacourse.movie.domain.policy

import java.time.LocalDate
import java.time.LocalTime

class DiscountDecorator(movieDate: LocalDate, movieTime: LocalTime) {
    private val policies = listOf(
        MovieDayPolicy(movieDate),
        EarlyAndLatePolicy(movieTime)
    )

    fun calculatePrice(): Int =
        policies.fold(DEFAULT_TICKET_PRICE) { acc, discountPolicy ->
            discountPolicy.calculatePrice(acc)
        }

    companion object {
        private const val DEFAULT_TICKET_PRICE = 13_000
    }
}

package com.woowacourse.movie.domain.policy

import java.time.LocalDateTime

class DiscountDecorator(movieDateTime: LocalDateTime) {
    private val policies = listOf(
        MovieDayPolicy(movieDateTime.toLocalDate()),
        EarlyAndLatePolicy(movieDateTime.toLocalTime())
    )

    fun calculatePrice(): Int =
        policies.fold(DEFAULT_TICKET_PRICE) { acc, discountPolicy ->
            discountPolicy.calculatePrice(acc)
        }

    companion object {
        private const val DEFAULT_TICKET_PRICE = 13_000
    }
}

package com.woowacourse.movie.domain.policy

import com.woowacourse.movie.domain.seat.Rank
import java.time.LocalDateTime

class DiscountDecorator(movieDateTime: LocalDateTime) {
    private val policies = listOf(
        MovieDayPolicy(movieDateTime.toLocalDate()),
        EarlyAndLatePolicy(movieDateTime.toLocalTime())
    )

    fun calculatePrice(rank: Rank): Int =
        policies.fold(rank.price) { acc, discountPolicy ->
            discountPolicy.calculatePrice(acc)
        }
}

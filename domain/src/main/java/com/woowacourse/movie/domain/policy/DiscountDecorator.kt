package com.woowacourse.movie.domain.policy

import com.woowacourse.movie.domain.MovieDate
import com.woowacourse.movie.domain.MovieTime

class DiscountDecorator(movieDate: MovieDate, movieTime: MovieTime) {
    private val policies = listOf(
        MovieDayPolicy(movieDate),
        EarlyAndLatePolicy(movieTime)
    )

    fun calculatePrice(): Int {
        var money = DEFAULT_TICKET_PRICE
        policies.forEach {
            money = it.calculatePrice(money)
        }
        return money
    }

    companion object {
        private const val DEFAULT_TICKET_PRICE = 13_000
    }
}

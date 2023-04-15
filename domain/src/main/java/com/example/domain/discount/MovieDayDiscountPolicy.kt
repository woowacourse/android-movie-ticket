package com.example.domain.discount

import com.example.domain.model.Count
import com.example.domain.model.Money
import com.example.domain.model.Movie
import java.time.LocalDateTime

class MovieDayDiscountPolicy : DiscountPolicy {
    override fun discount(
        originMoney: Money,
        count: Count,
        movie: Movie,
        dateTime: LocalDateTime
    ): Money {
        return when (dateTime.dayOfMonth) {
            in MOVIE_DAYS -> originMoney * MOVIE_DAY_DISCOUNT
            else -> originMoney
        }
    }

    companion object {
        private val MOVIE_DAYS = listOf(10, 20, 30)
        private const val MOVIE_DAY_DISCOUNT = 0.9f
    }
}

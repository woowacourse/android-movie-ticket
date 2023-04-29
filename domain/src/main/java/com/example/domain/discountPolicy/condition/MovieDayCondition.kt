package com.example.domain.discountPolicy.condition

import com.example.domain.model.Movie
import com.example.domain.model.seat.SeatPosition
import java.time.LocalDateTime

class MovieDayCondition : DiscountCondition {
    override fun isDiscountable(movie: Movie, dateTime: LocalDateTime, seatPosition: SeatPosition): Boolean {
        return dateTime.dayOfMonth in MOVIE_DAYS
    }

    companion object {
        private val MOVIE_DAYS = listOf(10, 20, 30)
    }
}

package com.example.domain.discountPolicy.condition

import com.example.domain.model.Movie
import com.example.domain.model.seat.SeatPosition
import java.time.LocalDateTime

class JoJoNightCondition : DiscountCondition {
    override fun isDiscountable(movie: Movie, dateTime: LocalDateTime, seatPosition: SeatPosition): Boolean {
        return dateTime.hour in TIME_CONDITION
    }

    companion object {
        private val TIME_CONDITION = (0..10) + (20..23)
    }
}

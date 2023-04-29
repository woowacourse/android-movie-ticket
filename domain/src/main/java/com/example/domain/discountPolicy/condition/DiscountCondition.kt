package com.example.domain.discountPolicy.condition

import com.example.domain.model.Movie
import com.example.domain.model.seat.SeatPosition
import java.time.LocalDateTime

interface DiscountCondition {
    fun isDiscountable(movie: Movie, dateTime: LocalDateTime, seatPosition: SeatPosition): Boolean
}

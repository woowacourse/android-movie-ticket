package com.example.domain.discountPolicy

import com.example.domain.model.Money
import com.example.domain.model.Movie
import com.example.domain.model.seat.SeatPosition
import java.time.LocalDateTime

interface DiscountPolicy {
    fun discount(movie: Movie, dateTime: LocalDateTime, seatPosition: SeatPosition): Money
}

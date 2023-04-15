package com.example.domain.discountPolicy.condition

import com.example.domain.model.Reservation

class MovieDayCondition : DiscountCondition {
    override fun isDiscountable(reservation: Reservation): Boolean {
        return reservation.dateTime.dayOfMonth in MOVIE_DAYS
    }

    companion object {
        private val MOVIE_DAYS = listOf(10, 20, 30)
    }
}

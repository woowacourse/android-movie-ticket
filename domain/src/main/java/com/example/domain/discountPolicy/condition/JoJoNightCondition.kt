package com.example.domain.discountPolicy.condition

import com.example.domain.model.Reservation

class JoJoNightCondition : DiscountCondition {
    override fun isDiscountable(reservation: Reservation): Boolean {
        return reservation.dateTime.hour in TIME_CONDITION
    }

    companion object {
        private val TIME_CONDITION = (0..10) + (20..23)
    }
}

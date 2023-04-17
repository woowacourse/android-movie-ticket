package com.example.domain.discountPolicy.condition

import com.example.domain.model.Reservation

interface DiscountCondition {
    fun isDiscountable(reservation: Reservation): Boolean
}

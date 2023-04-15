package com.example.domain.discountPolicy.condition

import com.example.domain.model.Reservation

fun interface DiscountCondition {
    fun isDiscountable(reservation: Reservation): Boolean
}

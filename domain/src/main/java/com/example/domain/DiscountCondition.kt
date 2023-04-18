package com.example.domain

interface DiscountCondition {
    fun isSatisfiedBy(reservation: Reservation): Boolean
}

package com.example.domain

class DayDiscountCondition(private val days: List<Int>) : DiscountCondition {
    override fun isSatisfiedBy(reservation: Reservation): Boolean =
        reservation.screeningDateTime.dayOfMonth in days
}

package com.example.domain.discountPolicy

import com.example.domain.model.Money
import com.example.domain.model.Reservation

interface DiscountPolicy {
    fun discount(reservation: Reservation): Money
}

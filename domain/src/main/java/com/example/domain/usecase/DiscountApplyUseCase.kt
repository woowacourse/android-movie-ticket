package com.example.domain.usecase

import com.example.domain.discountPolicy.DefaultDiscountPolicy
import com.example.domain.discountPolicy.DiscountPolicy
import com.example.domain.model.Money
import com.example.domain.model.Reservation

class DiscountApplyUseCase(private val discountPolicy: DiscountPolicy = DefaultDiscountPolicy()) {
    operator fun invoke(
        reservation: Reservation,
        onResult: (Money) -> Unit
    ) {
        val discountApplyMoney = discountPolicy.discount(reservation)
        onResult(discountApplyMoney)
    }
}

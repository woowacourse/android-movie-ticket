package com.example.domain.usecase

import com.example.domain.discountPolicy.DefaultDiscountPolicy
import com.example.domain.discountPolicy.DiscountPolicy
import com.example.domain.model.Money
import com.example.domain.model.Tickets

class DiscountApplyUseCase(private val discountPolicy: DiscountPolicy = DefaultDiscountPolicy()) {
    operator fun invoke(tickets: Tickets): Money =
        tickets.getTotalDiscountApplyMoney(discountPolicy)
}

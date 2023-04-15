package com.example.domain.discountPolicy.apply

import com.example.domain.model.Money

class JoJoNightDiscount : Discount {
    override fun discount(money: Money): Money = money - DISCOUNT

    companion object {
        private val DISCOUNT = Money(2000)
    }
}

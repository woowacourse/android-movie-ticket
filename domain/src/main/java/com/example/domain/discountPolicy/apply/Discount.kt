package com.example.domain.discountPolicy.apply

import com.example.domain.model.Money

interface Discount {
    fun discount(money: Money): Money
}

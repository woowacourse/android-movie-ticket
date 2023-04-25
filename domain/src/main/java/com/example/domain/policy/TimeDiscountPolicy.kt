package com.example.domain.policy

import java.time.LocalDateTime

class TimeDiscountPolicy : DiscountPolicy {
    override fun discountPrice(price: Int): Int {
        return price - DISCOUNT_PRICE
    }

    override fun checkPolicy(date: LocalDateTime): Boolean = date.hour !in DAYTIME

    companion object {
        private const val DISCOUNT_PRICE = 2000
        private val DAYTIME = (12..19)
    }
}

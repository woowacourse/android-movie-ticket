package com.example.domain.discountPolicy.apply

import com.example.domain.model.Money

class MovieDayDiscount : Discount {
    override fun discount(money: Money): Money = money * MOVIE_DAY_DISCOUNT

    companion object {
        private const val MOVIE_DAY_DISCOUNT = 0.9f
    }
}

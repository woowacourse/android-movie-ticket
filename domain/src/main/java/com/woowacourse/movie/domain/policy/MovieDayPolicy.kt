package com.woowacourse.movie.domain.policy

import java.time.LocalDate

class MovieDayPolicy(private val date: LocalDate) : DiscountPolicy() {
    override fun isDiscount(): Boolean = date.dayOfMonth in DISCOUNT_DAYS

    override fun determineDiscount(price: Int): Int = (price - price * DISCOUNT_PERCENT).toInt()

    companion object {
        private val DISCOUNT_DAYS = listOf(10, 20, 30)
        private const val DISCOUNT_PERCENT = 0.1
    }
}

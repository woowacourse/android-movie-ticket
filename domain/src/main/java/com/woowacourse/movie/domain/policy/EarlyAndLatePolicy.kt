package com.woowacourse.movie.domain.policy

import java.time.LocalTime

class EarlyAndLatePolicy(private val time: LocalTime) : DiscountPolicy() {
    private fun calculateTime(localTime: LocalTime): Int = localTime.hour * HOUR_TO_MIN + localTime.minute
    override fun isDiscount(): Boolean {
        val totalTime = calculateTime(time)
        return totalTime < AM_DISCOUNT_CLOSE_TIME || totalTime >= PM_DISCOUNT_OPEN_TIME
    }

    override fun determineDiscount(price: Int): Int = price - DISCOUNT_PRICE

    companion object {
        private const val HOUR_TO_MIN = 60
        private const val AM_DISCOUNT_CLOSE_TIME = 11
        private const val PM_DISCOUNT_OPEN_TIME = 20
        private const val DISCOUNT_PRICE = 2_000
    }
}

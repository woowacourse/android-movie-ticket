package com.woowacourse.movie.domain.policy

import java.time.LocalTime

class EarlyAndLatePolicy(private val time: LocalTime) : DiscountPolicy() {
    private fun calculateTime(localTime: LocalTime): Int = localTime.hour * HOUR_TO_MIN + localTime.minute
    override fun isDiscount(): Boolean {
        val totalTime = calculateTime(time)
        val amDiscountTime = calculateTime(LocalTime.of(AM_DISCOUNT_CLOSE_HOUR, AM_DISCOUNT_CLOSE_MIN))
        val pmDiscountTime = calculateTime(LocalTime.of(PM_DISCOUNT_OPEN_HOUR, PM_DISCOUNT_OPEN_MIN))
        return totalTime < amDiscountTime || totalTime >= pmDiscountTime
    }

    override fun determineDiscount(price: Int): Int = price - DISCOUNT_PRICE

    companion object {
        private const val HOUR_TO_MIN = 60
        private const val AM_DISCOUNT_CLOSE_HOUR = 11
        private const val AM_DISCOUNT_CLOSE_MIN = 0
        private const val PM_DISCOUNT_OPEN_HOUR = 20
        private const val PM_DISCOUNT_OPEN_MIN = 0
        private const val DISCOUNT_PRICE = 2_000
    }
}

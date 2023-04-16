package com.woowacourse.movie.domain.policy

class MovieDayPolicy(discountCondition: DiscountCondition) : DiscountPolicy(discountCondition) {
    override fun determineDiscount(price: Int): Int = (price - price * DISCOUNT_PERCENT).toInt()

    companion object {
        private const val DISCOUNT_PERCENT = 0.1
    }
}

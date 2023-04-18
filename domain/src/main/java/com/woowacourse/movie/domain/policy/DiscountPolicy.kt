package com.woowacourse.movie.domain.policy

abstract class DiscountPolicy {
    protected abstract fun isDiscount(): Boolean
    protected abstract fun determineDiscount(price: Int): Int
    fun calculatePrice(price: Int): Int =
        if (isDiscount()) determineDiscount(price) else price
}

package com.woowacourse.movie.domain.policy

abstract class DiscountPolicy {
    abstract fun isDiscount(): Boolean
    abstract fun determineDiscount(price: Int): Int
    fun calculatePrice(price: Int): Int =
        if (isDiscount()) determineDiscount(price) else price
}

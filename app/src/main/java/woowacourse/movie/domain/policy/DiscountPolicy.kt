package woowacourse.movie.domain.policy

abstract class DiscountPolicy(private val discountCondition: DiscountCondition) {
    abstract fun determineDiscount(price: Int): Int
    fun calculatePrice(price: Int): Int =
        if (discountCondition.isDiscount()) determineDiscount(price) else price
}

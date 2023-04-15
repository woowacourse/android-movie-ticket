package woowacourse.movie.domain.policy

class EarlyAndLatePolicy(discountCondition: DiscountCondition) : DiscountPolicy(discountCondition) {
    override fun determineDiscount(price: Int): Int = price - DISCOUNT_PRICE

    companion object {
        private const val DISCOUNT_PRICE = 2_000
    }
}

package woowacourse.movie.domain.discountpolicy

import java.time.LocalDateTime

class EarlyBirdTimeDiscountPolicy(override val dateTime: LocalDateTime) : DateTimeDiscountPolicy {
    override fun discount(price: Int): Int {
        if (dateTime.hour in EARLY_TIMES) return (price - DISCOUNT_PRICE)
        return price
    }

    companion object {
        private const val DISCOUNT_PRICE = 2000
        private val EARLY_TIMES = listOf(9, 10, 11)
    }
}

package woowacourse.movie.domain.discountpolicy

import java.time.LocalDateTime

class LateNightTimeDiscountPolicy(override val dateTime: LocalDateTime) : DateTimeDiscountPolicy {
    override fun discount(price: Int): Int {
        if (dateTime.hour in LATE_TIMES) return (price - DISCOUNT_PRICE)
        return price
    }

    companion object {
        private const val DISCOUNT_PRICE = 2000
        private val LATE_TIMES = listOf(20, 21, 22, 23)
    }
}

package woowacourse.movie.domain.discountpolicy

import java.time.LocalDateTime

class MovieDayTimeDiscountPolicy(override val dateTime: LocalDateTime) : DateTimeDiscountPolicy {
    override fun discount(price: Int): Int {
        if (dateTime.dayOfMonth in MOVIE_DAYS) return (price * DISCOUNTED_PRICE_RATE).toInt()
        return price
    }

    companion object {
        private const val DISCOUNTED_PRICE_RATE = 0.9
        private val MOVIE_DAYS = listOf(10, 20, 30)
    }
}

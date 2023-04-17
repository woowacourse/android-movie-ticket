package woowacourse.movie.domain.policy

import java.time.LocalDateTime

class MovieDayDiscountPolicy : DiscountPolicy {
    override fun discountPrice(price: Int): Int {
        return (price * DISCOUNT_PERCENT).toInt()
    }

    override fun checkPolicy(date: LocalDateTime): Boolean {
        if (date.dayOfMonth in MOVIE_DAYS) return true
        return false
    }

    companion object {
        private const val DISCOUNT_PERCENT = 0.9
        private val MOVIE_DAYS = listOf(10, 20, 30)
    }
}

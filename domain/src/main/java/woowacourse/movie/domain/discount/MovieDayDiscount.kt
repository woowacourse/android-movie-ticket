package woowacourse.movie.domain.discount

import java.time.LocalDateTime

class MovieDayDiscount(
    private val time: LocalDateTime
) : DiscountPolicy {
    override fun getDiscountPrice(price: Int): Int {
        if (time.dayOfMonth in MOVIE_DAYS) {
            return (price * TICKET_MOVIE_DAY_SALE_RATE).toInt()
        }
        return price
    }

    companion object {
        private val MOVIE_DAYS = listOf(10, 20, 30)
        private const val TICKET_MOVIE_DAY_SALE_RATE = 0.9
    }
}

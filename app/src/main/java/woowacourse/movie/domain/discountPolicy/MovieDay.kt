package woowacourse.movie.domain.discountPolicy

import woowacourse.movie.domain.Price
import woowacourse.movie.domain.ReservationDetail

class MovieDay(private val movieDay: List<Int> = DEFAULT_MOVIE_DAY) : DiscountPolicy {
    override fun discount(reservationDetail: ReservationDetail, price: Price): Price {
        if (reservationDetail.date.dayOfMonth in movieDay) {
            return Price((price.value * DISCOUNT_RATE).toInt())
        }
        return price
    }

    companion object {
        private val DEFAULT_MOVIE_DAY = listOf(10, 20, 30)
        private const val DISCOUNT_RATE = 0.9f
    }
}

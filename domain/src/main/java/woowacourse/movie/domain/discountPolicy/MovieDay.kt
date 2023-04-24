package woowacourse.movie.domain.discountPolicy

import woowacourse.movie.domain.Price
import woowacourse.movie.domain.ReservationDetail

object MovieDay : DiscountPolicy {
    private val MOVIE_DAY = listOf(10, 20, 30)
    private const val DISCOUNT_RATE = 0.9f

    override fun discount(reservationDetail: ReservationDetail, price: Price): Price {
        if (reservationDetail.date.dayOfMonth in MOVIE_DAY) {
            return Price((price.value * DISCOUNT_RATE).toInt())
        }
        return price
    }
}

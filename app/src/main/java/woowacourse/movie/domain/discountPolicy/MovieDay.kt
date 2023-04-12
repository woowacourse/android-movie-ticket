package woowacourse.movie.domain.discountPolicy

import woowacourse.movie.domain.Price
import woowacourse.movie.domain.ReservationDetail

class MovieDay : DiscountPolicy {
    override fun discount(reservationDetail: ReservationDetail): ReservationDetail {
        if (reservationDetail.date.dayOfMonth in MOVIE_DAY) {
            val discountPrice = Price((reservationDetail.price.value * DISCOUNT_RATE).toInt())
            return ReservationDetail(
                reservationDetail.date,
                reservationDetail.peopleCount,
                discountPrice,
            )
        }
        return reservationDetail
    }

    companion object {
        private val MOVIE_DAY = listOf(10, 20, 30)
        private const val DISCOUNT_RATE = 0.9f
    }
}

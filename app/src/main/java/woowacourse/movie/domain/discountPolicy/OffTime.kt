package woowacourse.movie.domain.discountPolicy

import woowacourse.movie.domain.Price
import woowacourse.movie.domain.ReservationDetail

class OffTime : DiscountPolicy {
    override fun discount(reservationDetail: ReservationDetail): ReservationDetail {
        if (reservationDetail.date.hour < EARLY_TIME || reservationDetail.date.hour > LATE_TIME) {
            val discountPrice = Price(reservationDetail.price.value - DISCOUNT_VALUE)
            return ReservationDetail(
                reservationDetail.date,
                reservationDetail.peopleCount,
                discountPrice,
            )
        }
        return reservationDetail
    }

    companion object {
        private const val EARLY_TIME = 11
        private const val LATE_TIME = 20
        private const val DISCOUNT_VALUE = 2000
    }
}

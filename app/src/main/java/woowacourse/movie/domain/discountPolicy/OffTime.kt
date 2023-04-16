package woowacourse.movie.domain.discountPolicy

import woowacourse.movie.domain.Price
import woowacourse.movie.domain.ReservationDetail

class OffTime(val earlyTime: Int = DEFAULT_EARLY_TIME, val lateTime: Int = DEFAULT_LATE_TIME) :
    DiscountPolicy {
    override fun discount(reservationDetail: ReservationDetail, price: Price): Price {
        if (reservationDetail.date.hour < earlyTime || reservationDetail.date.hour > lateTime) {
            return Price(price.value - DISCOUNT_VALUE)
        }
        return price
    }

    companion object {
        private const val DEFAULT_EARLY_TIME = 11
        private const val DEFAULT_LATE_TIME = 20
        private const val DISCOUNT_VALUE = 2000
    }
}

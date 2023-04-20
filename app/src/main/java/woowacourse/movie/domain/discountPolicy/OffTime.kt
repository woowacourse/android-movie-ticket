package woowacourse.movie.domain.discountPolicy

import woowacourse.movie.domain.Price
import woowacourse.movie.domain.ReservationDetail

object OffTime : DiscountPolicy {
    private const val EARLY_TIME = 11
    private const val LATE_TIME = 20
    private const val DISCOUNT_VALUE = 2000

    override fun discount(reservationDetail: ReservationDetail, price: Price): Price {
        if (reservationDetail.date.hour < EARLY_TIME || reservationDetail.date.hour > LATE_TIME) {
            return Price(price.value - DISCOUNT_VALUE)
        }
        return price
    }
}

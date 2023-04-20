package woowacourse.movie.domain.discountPolicy

import woowacourse.movie.domain.Price
import woowacourse.movie.domain.ReservationDetail

interface DiscountPolicy {
    fun discount(reservationDetail: ReservationDetail, price: Price): Price
}

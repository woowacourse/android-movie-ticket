package woowacourse.movie.domain.discountPolicy

import woowacourse.movie.domain.ReservationDetail

interface DiscountPolicy {
    fun discount(reservationDetail: ReservationDetail): ReservationDetail
}

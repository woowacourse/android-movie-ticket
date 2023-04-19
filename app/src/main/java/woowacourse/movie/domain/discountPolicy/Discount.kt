package woowacourse.movie.domain.discountPolicy

import woowacourse.movie.domain.ReservationDetail

class Discount(private val discountPolicy: List<DiscountPolicy>) {
    fun calculate(
        reservationDetail: ReservationDetail,
    ): ReservationDetail {
        return discountPolicy.fold(reservationDetail) { total, item ->
            item.discount(total)
        }
    }
}

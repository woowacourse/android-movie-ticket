package woowacourse.movie.domain.discountPolicy

import woowacourse.movie.domain.ReservationDetail

class Discount(private val discountPolicy: List<DiscountPolicy>) {
    fun calculate(
        reservationDetail: ReservationDetail,
    ): ReservationDetail {
        var result = reservationDetail
        for (item in discountPolicy) {
            result = item.discount(result)
        }
        return result
    }
}

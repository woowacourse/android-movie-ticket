package woowacourse.movie.domain.discountPolicy

import woowacourse.movie.domain.ReservationDetail

object Discount {
    fun calculate(
        discountPolicy: List<DiscountPolicy>,
        reservationDetail: ReservationDetail,
    ): ReservationDetail {
        var result = reservationDetail
        for (item in discountPolicy) {
            result = item.discount(result)
        }
        return result
    }
}

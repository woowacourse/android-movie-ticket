package woowacourse.movie.domain.discountPolicy

import woowacourse.movie.domain.Price
import woowacourse.movie.domain.ReservationDetail
import java.io.Serializable

class Discount(private val discountPolicy: List<DiscountPolicy>) : Serializable {
    fun calculate(
        reservationDetail: ReservationDetail,
        price: Price
    ): Price {
        var presentPrice = price
        for (item in discountPolicy) {
            presentPrice = item.discount(reservationDetail, presentPrice)
        }
        return presentPrice
    }
}

package woowacourse.movie.domain.discountPolicy

import woowacourse.movie.domain.Price
import woowacourse.movie.domain.ReservationDetail
import java.io.Serializable

interface DiscountPolicy : Serializable {
    fun discount(reservationDetail: ReservationDetail, price: Price): Price
}

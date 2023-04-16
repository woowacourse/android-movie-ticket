package woowacourse.movie.domain

import woowacourse.movie.domain.discountPolicy.Discount
import java.io.Serializable
import java.time.LocalDateTime

data class ReservationDetail(
    val date: LocalDateTime,
    val peopleCount: Int,
    val discount: Discount
) : Serializable {
    val totalPrice: Price
        get() = Price(peopleCount * getDiscountPrice().value)
    private fun getDiscountPrice(): Price = discount.calculate(this, Price())
}

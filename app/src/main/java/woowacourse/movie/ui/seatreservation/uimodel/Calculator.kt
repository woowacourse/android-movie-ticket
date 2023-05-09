package woowacourse.movie.ui.seatreservation.uimodel

import woowacourse.movie.domain.discountpolicy.DateTimeTimeDiscountAdapter
import java.time.LocalDateTime

class Calculator private constructor(
    private val bookedDateTime: LocalDateTime,
) {
    var totalAmount: Int = 0
        private set

    fun plus(selectSeatPrice: Money) {
        totalAmount += getPaymentOnDiscountPolicy(selectSeatPrice.value)
    }

    fun minus(selectSeatPrice: Money) {
        totalAmount -= selectSeatPrice.value
    }

    private fun getPaymentOnDiscountPolicy(price: Int): Int =
        DateTimeTimeDiscountAdapter(bookedDateTime).discount(price)

    companion object {
        fun create(localDateTime: LocalDateTime) = Calculator(localDateTime)
    }
}

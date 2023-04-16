package woowacourse.movie.domain.model

import woowacourse.movie.domain.discount.discountpolicy.DateTimeDiscountAdapter
import java.time.LocalDateTime

data class Ticket(
    val movieId: Long,
    val bookedDateTime: LocalDateTime,
    val count: Int,
) {
    fun getPaymentMoney(): Money {
        val discountedMoney = DateTimeDiscountAdapter(bookedDateTime).discount(Money(TICKET_PRICE))
        return discountedMoney.multiplyMoneyWithCount(count)
    }

    companion object {
        private const val TICKET_PRICE = 13000
    }
}

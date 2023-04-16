package woowacourse.movie.domain.model

import woowacourse.movie.domain.discountpolicy.DateTimeDiscountAdapter
import java.time.LocalDateTime

data class Ticket(
    val movieId: Long,
    val bookedDateTime: LocalDateTime,
    val count: Int,
) {

    fun getPaymentMoney() {
        val multipliedMoney = Money(TICKET_PRICE).multiplyMoneyWithCount(count)
        DateTimeDiscountAdapter(bookedDateTime).discount(multipliedMoney)
    }

    companion object {
        private const val TICKET_PRICE = 13000
    }
}

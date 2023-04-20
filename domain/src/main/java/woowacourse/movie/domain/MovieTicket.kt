package woowacourse.movie.domain

import woowacourse.movie.domain.discount.TicketDiscount
import java.time.LocalDateTime

data class MovieTicket(
    val title: String,
    val time: LocalDateTime,
    val peopleCount: PeopleCount,
) {
    fun getPrice(): Int {
        val price = TICKET_PRICE * peopleCount.count
        return TicketDiscount(time).getDiscountPrice(price)
    }

    companion object {
        private const val TICKET_PRICE = 13_000
    }
}

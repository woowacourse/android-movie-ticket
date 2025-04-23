package woowacourse.movie.domain

import java.time.LocalDateTime

class Ticket(
    screening: Screening,
    var ticketCount: TicketCount,
    val showtime: LocalDateTime,
) {
    val title: String = screening.title
    val price: Int = ticketCount.value * DEFAULT_TICKET_PRICE
    val cancelableMinute = DEFAULT_CANCELABLE_MINUTE

    companion object {
        private const val DEFAULT_TICKET_PRICE = 13_000
        private const val DEFAULT_CANCELABLE_MINUTE = 15
    }
}

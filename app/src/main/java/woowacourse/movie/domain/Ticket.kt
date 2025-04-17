package woowacourse.movie.domain

import java.time.LocalDateTime

class Ticket(
    screening: Screening,
    val count: Int,
    val showtime: LocalDateTime,
) {
    val title: String = screening.title
    val price: Int = count * TICKET_PRICE

    companion object {
        const val TICKET_PRICE = 13_000
    }
}

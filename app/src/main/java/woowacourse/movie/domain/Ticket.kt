package woowacourse.movie.domain

import java.time.LocalDateTime

data class Ticket(
    private val screening: Screening,
    private val count: Int,
    private val showtime: LocalDateTime,
) {
    val price: Int = count * TICKET_PRICE

    companion object {
        const val TICKET_PRICE = 13_000
    }
}

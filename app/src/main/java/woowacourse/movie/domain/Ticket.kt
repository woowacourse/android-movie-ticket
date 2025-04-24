package woowacourse.movie.domain

import java.time.LocalDateTime

data class Ticket(
    val movie: Movie,
    val showtime: LocalDateTime,
    val count: TicketCount,
) {
    fun totalPrice(): Int = count.value * TICKET_PRICE

    fun increment(): Ticket = copy(count = count.increment())

    fun decrement(): Ticket = copy(count = count.decrement())

    companion object {
        private const val TICKET_PRICE = 13_000
    }
}

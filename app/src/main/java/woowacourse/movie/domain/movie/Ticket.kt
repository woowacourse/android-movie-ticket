package woowacourse.movie.domain.movie

import java.time.LocalDateTime

data class Ticket(
    val movie: Movie,
    val showtime: LocalDateTime,
    val count: TicketCount,
) {
    fun increment(): Ticket = copy(count = count.increment())

    fun decrement(): Ticket = copy(count = count.decrement())
}

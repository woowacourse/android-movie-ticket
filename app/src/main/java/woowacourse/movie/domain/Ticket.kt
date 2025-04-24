package woowacourse.movie.domain

import java.time.LocalDateTime
import java.time.LocalTime

data class Ticket(
    val movie: Movie,
    val showtime: LocalDateTime = LocalDateTime.of(movie.startDate, LocalTime.MIDNIGHT),
    val count: Int = MINIMUM_TICKET_COUNT,
) {
    init {
        require(count >= MINIMUM_TICKET_COUNT)
    }

    fun isMinimumCount(): Boolean = count == MINIMUM_TICKET_COUNT

    fun totalPrice(): Int = count * TICKET_PRICE

    companion object {
        private const val MINIMUM_TICKET_COUNT = 1
        private const val TICKET_PRICE = 13_000
    }
}

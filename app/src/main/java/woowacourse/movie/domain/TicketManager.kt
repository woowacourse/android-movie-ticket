package woowacourse.movie.domain

import java.time.LocalDate
import java.time.LocalTime

class TicketManager(
    private val movie: Movie,
) {
    private var ticketCountValue = 0

    fun incrementTicketCount() {
        ticketCountValue++
    }

    fun decrementTicketCount() {
        ticketCountValue = (ticketCountValue - 1).coerceAtLeast(0)
    }

    fun getTicketCount(): Int = ticketCountValue

    fun setTicketCount(ticketCount: Int) {
        ticketCountValue = ticketCount
    }

    fun createTicket(
        date: LocalDate,
        time: LocalTime,
    ): Ticket =
        Ticket(
            title = movie.title,
            date = date,
            time = time,
            count = ticketCountValue,
        )
}

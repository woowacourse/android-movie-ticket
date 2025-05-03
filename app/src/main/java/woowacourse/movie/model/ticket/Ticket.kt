package woowacourse.movie.model.ticket

import woowacourse.movie.model.movieSelect.screening.Screening
import java.time.LocalDateTime

class Ticket(
    screening: Screening,
    var ticketCount: TicketCount,
    val showtime: LocalDateTime,
) {
    val title: String = screening.title

    val cancelableMinute = DEFAULT_CANCELABLE_MINUTE

    companion object {
        private const val DEFAULT_CANCELABLE_MINUTE = 15
    }
}

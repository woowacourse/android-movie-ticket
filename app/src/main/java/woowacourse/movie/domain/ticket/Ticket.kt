package woowacourse.movie.domain.ticket

import java.io.Serializable
import java.time.LocalDateTime

class Ticket(
    val title: String,
    val count: Int,
    val showtime: LocalDateTime,
) : Serializable {
    val price: Int = count * TICKET_PRICE

    companion object {
        const val TICKET_PRICE = 13_000
    }
}

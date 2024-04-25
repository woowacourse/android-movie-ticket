package woowacourse.movie.model

import java.time.LocalDate
import java.time.LocalTime

class MovieTicket(
    val title: String,
    val date: LocalDate,
    val time: LocalTime,
    val count: Int,
) {
    val price: Int = TICKET_PRICE * count

    companion object {
        const val TICKET_PRICE = 13_000
    }
}

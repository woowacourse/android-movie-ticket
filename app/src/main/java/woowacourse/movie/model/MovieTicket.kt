package woowacourse.movie.model

import java.io.Serializable
import java.time.LocalDate

data class MovieTicket(
    val title: String,
    val movieDate: LocalDate,
    val movieTime: MovieTime,
    val count: TicketCount,
) : Serializable {
    fun price(): Int = count.value * TICKET_PRICE

    companion object {
        private const val TICKET_PRICE = 13000
    }
}

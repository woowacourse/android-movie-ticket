package woowacourse.movie.presentation.model

import woowacourse.movie.domain.model.Ticket
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime

class TicketModel(
    val title: String,
    val screeningDate: LocalDate,
    val screeningTime: LocalDateTime,
    val seats: List<String>,
    val count: Int,
    val price: Int,
) : Serializable {
    companion object {
        private const val DEFAULT_TICKET_TITLE = "티켓정보가 존재하지 않습니다."
        val defaultTicket = TicketModel(
            price = 0,
            screeningDate = LocalDate.now(),
            screeningTime = LocalDateTime.now(),
            title = DEFAULT_TICKET_TITLE,
            seats = listOf(),
            count = 1,
        )
    }
}

fun Ticket.toTicketModel(): TicketModel {
    return TicketModel(
        title = title,
        screeningDate = movieDate.currentDate,
        screeningTime = movieDate.currentTime,
        count = count,
        price = price,
        seats = seats.map { it.seatName }
    )
}

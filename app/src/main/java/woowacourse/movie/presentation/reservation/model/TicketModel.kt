package woowacourse.movie.presentation.reservation.model

import woowacourse.movie.domain.model.Ticket
import java.io.Serializable
import java.time.LocalDate

class TicketModel(
    val title: String,
    val screeningDate: LocalDate,
    val count: Int,
    val price: Int,
) : Serializable {
    companion object {
        private const val DEFAULT_TICKET_TITLE = "티켓정보가 존재하지 않습니다."
        val defaultTicket = TicketModel(
            price = 0,
            screeningDate = LocalDate.now(),
            title = DEFAULT_TICKET_TITLE,
            count = 0,
        )
    }
}

fun Ticket.toTicketModel(): TicketModel {
    return TicketModel(
        title = title,
        screeningDate = screeningDate,
        count = count,
        price = price,
    )
}

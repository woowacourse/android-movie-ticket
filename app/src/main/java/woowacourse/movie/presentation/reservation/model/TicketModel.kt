package woowacourse.movie.presentation.reservation.model

import woowacourse.movie.domain.model.Ticket
import java.io.Serializable

class TicketModel(
    val title: String,
    val screeningDate: String,
    val count: Int,
    val price: Int,
): Serializable

fun Ticket.toTicketModel(): TicketModel {
    return TicketModel(
        title = title,
        screeningDate = screeningDate,
        count = count,
        price = price,
    )
}

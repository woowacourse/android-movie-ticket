package woowacourse.movie.view.viewmodel

import woowacourse.movie.domain.Ticket
import java.io.Serializable
import java.time.LocalDateTime

fun Ticket.toUIModel() = TicketUIModel(
    price, date, numberOfPeople
)

fun TicketUIModel.toTicket() = Ticket(
    price, date, numberOfPeople
)

class TicketUIModel(
    val price: Int,
    val date: LocalDateTime,
    val numberOfPeople: Int,
) : Serializable

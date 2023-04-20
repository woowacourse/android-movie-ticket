package woowacourse.movie.mapper

import com.example.domain.model.Ticket
import woowacourse.movie.formatter.DateFormatter
import woowacourse.movie.formatter.TimeFormatter
import woowacourse.movie.model.TicketModel

const val ticketDateFormat: String = "yyyy.M.d"
const val ticketTimeFormat: String = "HH:mm"

fun TicketModel.toTicket() = Ticket(
    title,
    DateFormatter.formatToOriginal(playingDate, ticketDateFormat),
    TimeFormatter.formatToOriginal(playingTime, ticketTimeFormat),
    count,
    price,
    payment
)

fun Ticket.toTicketModel() = TicketModel(
    title,
    DateFormatter.formatToString(playingDate, ticketDateFormat),
    TimeFormatter.formatToString(playingTime, ticketTimeFormat),
    count,
    price, payment
)

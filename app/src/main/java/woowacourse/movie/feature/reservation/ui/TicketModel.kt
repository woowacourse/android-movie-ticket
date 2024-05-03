package woowacourse.movie.feature.reservation.ui

import woowacourse.movie.domain.Ticket
import woowacourse.movie.feature.util.DATE_FORMAT_DOT
import woowacourse.movie.feature.util.TIME_FORMAT
import java.io.Serializable
import java.time.format.DateTimeFormatter
import java.util.Locale

data class TicketModel(
    val title: String,
    val date: String,
    val time: String,
    val seats: List<String>,
    val price: Long,
) : Serializable

fun Ticket.toUiModel(): TicketModel {
    val dateFormatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern(DATE_FORMAT_DOT, Locale.getDefault())
    val timeFormatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern(TIME_FORMAT, Locale.getDefault())
    return TicketModel(
        title = movie.title,
        date = reservationDateTime.format(dateFormatter),
        time = reservationDateTime.format(timeFormatter),
        seats = seats.map { it.row.name + it.col.toString() }.sorted(),
        price = price,
    )
}

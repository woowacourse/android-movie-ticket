package woowacourse.movie.feature.reservation.ui

import android.content.Context
import woowacourse.movie.R
import woowacourse.movie.domain.Ticket
import java.io.Serializable
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

data class TicketModel(
    val title: String,
    val date: String,
    val time: String,
    val seats: List<String>,
    val price: Long,
) : Serializable {
    fun formatPrice(context: Context): String {
        val formattedPrice = DecimalFormat(DECIMAL_FORMAT).format(price)
        return String.format(context.getString(R.string.reservation_price), formattedPrice)
    }

    fun formatSeat(context: Context) =
        String.format(
            context.getString(R.string.reservation_quantity),
            seats.size,
            seats.joinToString(", "),
        )

    fun formatDateTime() = "$date $time"

    companion object {
        const val DECIMAL_FORMAT = "#,###"
        const val DATE_FORMAT = "yyyy.M.d"
        const val TIME_FORMAT = "HH:mm"
    }
}

fun Ticket.toUiModel(): TicketModel {
    val dateFormatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern(TicketModel.DATE_FORMAT, Locale.getDefault())
    val timeFormatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern(TicketModel.TIME_FORMAT, Locale.getDefault())
    return TicketModel(
        title = movie.title,
        date = reservationDateTime.format(dateFormatter),
        time = reservationDateTime.format(timeFormatter),
        seats = seats.map { it.row.name + it.col.toString() }.sorted(),
        price = price,
    )
}

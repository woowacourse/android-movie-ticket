package woowacourse.movie.ui.util

import woowacourse.movie.domain.model.seat.Seat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object TicketUiFormatter {
    private val dateTimeFormatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")

    fun formatDateTime(dateTime: LocalDateTime): String = dateTime.format(dateTimeFormatter)

    fun formatHeadCount(message: String, headCount: Int): String {
        return String.format(message, headCount)
    }

    fun formatAmount(message: String, value: Int): String {
        return String.format(message, value.formatWithComma())
    }

    private fun Int.formatWithComma(): String = "%,d".format(this)
}

fun List<Seat>.toUi(): String {
    return this.joinToString(", ") { seat ->
        val row = 'A' + seat.seatPosition.y
        val col = seat.seatPosition.x + 1
        "$row$col"
    }
}
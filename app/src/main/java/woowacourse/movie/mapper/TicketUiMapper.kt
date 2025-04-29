package woowacourse.movie.mapper

import woowacourse.movie.booking.detail.TicketUiModel
import woowacourse.movie.model.HeadCount
import woowacourse.movie.model.Seat
import woowacourse.movie.model.Seats
import woowacourse.movie.model.Ticket
import woowacourse.movie.util.Formatter.formatDateDotSeparated
import woowacourse.movie.util.Formatter.formatMoney
import woowacourse.movie.util.Formatter.formatTimeWithMidnight24
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun Ticket.toUiModel(): TicketUiModel {
    val selectedDateText = formatDateDotSeparated(selectedDate)
    val selectedTimeText = formatTimeWithMidnight24(selectedTime)
    val amountText = formatMoney(amount)

    return TicketUiModel(
        title = title,
        headCount = headCount.value,
        selectedDateText = selectedDateText,
        selectedTimeText = selectedTimeText,
        totalPrice = amountText,
        seats = seats.values.joinToString(",") { it.seatName },
    )
}

fun TicketUiModel.toDomain(): Ticket {
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy.M.d")
    val timeFormatter = DateTimeFormatter.ofPattern("kk:mm")

    val selectedDate = LocalDate.parse(selectedDateText, dateFormatter)
    val selectedTime = LocalTime.parse(selectedTimeText, timeFormatter)

    val parsedSeats =
        if (seats.isBlank()) {
            emptyList()
        } else {
            seats.split(",").map { seatName ->
                Seat(seatName = seatName, isSelected = true)
            }
        }

    return Ticket(
        title = title,
        headCount = HeadCount(headCount),
        selectedDate = selectedDate,
        selectedTime = selectedTime,
        seats = Seats(parsedSeats),
    )
}

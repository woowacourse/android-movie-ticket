package woowacourse.movie.reservationresult.uimodel

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.time.Duration

data class ReservationResultUiModel(
    val title: String,
    val cancelDeadLine: String,
    val dateTime: String,
    val headCount: HeadCountUiModel,
    val seats: List<SeatUiModel>,
    val totalPrice: PriceUiModel,
) {
    constructor(
        title: String,
        cancelDeadLine: Duration,
        dateTime: LocalDateTime,
        count: Int,
        seats: List<SeatUiModel>,
        totalPrice: Int,
    ) : this(
        title,
        cancelDeadLine.inWholeMinutes.toString(),
        dateTime.format(dateFormatter) + " " + dateTime.format(timeFormatter),
        HeadCountUiModel(count),
        seats,
        PriceUiModel(totalPrice),
    )

    companion object {
        private val dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    }
}

package woowacourse.movie.reservationresult

import woowacourse.movie.model.MovieReservation
import java.time.format.DateTimeFormatter

fun MovieReservation.toReservationResultUiModel(): ReservationResultUiModel {
    val pattern = "yyyy.MM.dd"
    val screenDate: String =
        screenDateTime.toLocalDate().format(DateTimeFormatter.ofPattern(pattern))
    return ReservationResultUiModel(
        movie.title,
        cancelDeadLine.inWholeMinutes.toInt(),
        screenDate,
        headCount.count,
        totalPrice.price.toInt(),
    )
}

package woowacourse.movie.presentation.reservation.booking

import woowacourse.movie.model.MovieReservation
import woowacourse.movie.model.ScreeningMovie
import woowacourse.movie.presentation.reservation.result.ReservationResultUiModel
import java.time.format.DateTimeFormatter

private const val DATE_PATTERN = "yyyy.MM.dd"
private val Formatter = DateTimeFormatter.ofPattern(DATE_PATTERN)

fun ScreeningMovie.toMovieReservationUiModel(): MovieReservationUiModel {
    val screenDate: String =
        screenDateTimes.first().date.format(Formatter)
    val runningTime = movie.runningTime.time.inWholeMinutes
    return MovieReservationUiModel(
        id = id,
        title = movie.title,
        screenDate = "러닝타임: ${runningTime}분",
        runningTime = "상영일: $screenDate",
        description = movie.description,
    )
}

fun MovieReservation.toUiModel(): ReservationResultUiModel {
    val screenDate: String =
        screenDateTime.toLocalDate().format(Formatter)
    return ReservationResultUiModel(
        movie.title,
        cancelDeadLine.inWholeMinutes.toInt(),
        screenDate,
        headCount.count,
        totalPrice.price.toInt(),
    )
}

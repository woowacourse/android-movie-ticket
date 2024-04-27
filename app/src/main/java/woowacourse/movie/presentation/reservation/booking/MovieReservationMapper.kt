package woowacourse.movie.presentation.reservation.booking

import woowacourse.movie.model.MovieReservation
import woowacourse.movie.model.ScreenDateTimes
import woowacourse.movie.model.ScreeningMovie
import woowacourse.movie.presentation.reservation.result.ReservationResultUiModel
import java.time.format.DateTimeFormatter

private const val DATE_PATTERN = "yyyy.MM.dd"
private const val DATE_FORMAT = "상영일: %s ~ %s"
private const val RUNNING_TIME_FORMAT = "러닝타임: %d분"
private val Formatter = DateTimeFormatter.ofPattern(DATE_PATTERN)

fun ScreeningMovie.toMovieReservationUiModel(): MovieReservationUiModel {
    val screenDate: String = screenDateTimes.format()
    val rawRunningTime = movie.runningTime.time.inWholeMinutes
    val runningTime = RUNNING_TIME_FORMAT.format(rawRunningTime)
    return MovieReservationUiModel(
        id = id,
        title = movie.title,
        screenDate = runningTime,
        runningTime = screenDate,
        description = movie.description,
    )
}

private fun ScreenDateTimes.format(): String {
    if (size == 1) {
        return first().date.format(Formatter)
    }
    val startDate = first().date.format(Formatter)
    val endDate = last().date.format(Formatter)
    return DATE_FORMAT.format(startDate, endDate)
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
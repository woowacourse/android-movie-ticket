package woowacourse.movie.presentation.reservation.booking

import woowacourse.movie.model.MovieReservation
import woowacourse.movie.model.date.ScreenDateTime
import woowacourse.movie.model.date.ScreenDateTimes
import woowacourse.movie.model.date.ScreeningMovie
import woowacourse.movie.presentation.reservation.result.ReservationResultUiModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

private const val DATE_PATTERN = "yyyy.MM.dd"
private const val TIME_PATTERN = "HH:mm"
private const val DATE_FORMAT = "상영일: %s ~ %s"
private const val RUNNING_TIME_FORMAT = "러닝타임: %d분"
private val DateFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN)
private val TimeFormatter = DateTimeFormatter.ofPattern(TIME_PATTERN)
private const val DEFAULT_HEAD_COUNT = 1

fun ScreeningMovie.toReservationUiState(): MovieReservationUiState {
    val selectedDateTime = screenDateTimes.first()
    val selectedTime = selectedDateTime.times.first()
    return MovieReservationUiState(
        id = id,
        movie = toUiModel(),
        count = DEFAULT_HEAD_COUNT,
        screenDateTimes = screenDateTimes.toUiModel(),
        selectedDate = screenDateTimes.first().toUiModel(),
        selectedTime = selectedTime.format(TimeFormatter),
    )
}

private fun ScreeningMovie.toUiModel(): ScreeningMovieUiModel {
    val screenDate: String = screenDateTimes.formatToScreenDuration()
    val rawRunningTime = movie.runningTime.time.inWholeMinutes
    val runningTime = RUNNING_TIME_FORMAT.format(rawRunningTime)
    return ScreeningMovieUiModel(
        title = movie.title,
        screenDate = runningTime,
        runningTime = screenDate,
        description = movie.description,
    )
}

private fun ScreenDateTimes.formatToScreenDuration(): String {
    if (size == 1) {
        return first().date.format(DateFormatter)
    }
    val startDate = first().date.format(DateFormatter)
    val endDate = last().date.format(DateFormatter)
    return DATE_FORMAT.format(startDate, endDate)
}

private fun ScreenDateTimes.toUiModel(): List<ScreenDateTimeUiModel> {
    return dateTimes.map { it.toUiModel() }
}

private fun ScreenDateTime.toUiModel(): ScreenDateTimeUiModel {
    return ScreenDateTimeUiModel(
        date = date.format(DateFormatter),
        times = times.map { it.format(TimeFormatter) },
    )
}

fun MovieReservation.toUiModel(): ReservationResultUiModel {
    val screenDate: String =
        screenDateTime.toLocalDate().format(DateFormatter)
    return ReservationResultUiModel(
        movie.title,
        cancelDeadLine.inWholeMinutes.toInt(),
        screenDate,
        headCount.count,
        totalPrice.price.toInt(),
    )
}

fun parseScreenDateTime(date: String, time: String): LocalDateTime {
    val localDate = LocalDate.parse(date, DateFormatter)
    val localTime = LocalTime.parse(time, TimeFormatter)
    return localDate.atTime(localTime)
}
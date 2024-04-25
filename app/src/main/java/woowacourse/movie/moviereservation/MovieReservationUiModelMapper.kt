package woowacourse.movie.moviereservation

import woowacourse.movie.model.HeadCount
import woowacourse.movie.model.ScreeningMovie
import woowacourse.movie.moviereservation.uimodel.HeadCountUiModel
import woowacourse.movie.moviereservation.uimodel.MovieReservationUiModel
import woowacourse.movie.moviereservation.uimodel.ScreeningDateTimeUiModel
import java.time.format.DateTimeFormatter

fun ScreeningMovie.toMovieReservationUiModel(): MovieReservationUiModel {
    val pattern = "yyyy.MM.dd"
    val screenDate: String =
        screenDateTimes.first().date.format(DateTimeFormatter.ofPattern(pattern))
    val runningTime = movie.runningTime.time.inWholeMinutes
    return MovieReservationUiModel(
        id = id,
        title = movie.title,
        screenDate = "러닝타임: ${runningTime}분",
        runningTime = "상영일: $screenDate",
        description = movie.description,
    )
}

fun HeadCount.toHeadCountUiModel(): HeadCountUiModel = HeadCountUiModel(count.toString())

fun HeadCountUiModel.toHeadCount(): HeadCount = HeadCount(count.toInt())

private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

fun ScreeningMovie.toScreeningDateTimeUiModel(): List<ScreeningDateTimeUiModel> =
    screenDateTimes.map {
        ScreeningDateTimeUiModel(
            it.date.format(
                dateFormatter,
            ),
            it.times.map { time -> time.format(timeFormatter) },
        )
    }

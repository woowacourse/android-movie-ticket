package woowacourse.movie.moviereservation

import woowacourse.movie.model.ScreeningMovie
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

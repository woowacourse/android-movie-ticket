package woowacourse.movie.presenter

import woowacourse.movie.model.MovieReservation
import woowacourse.movie.model.MovieReservationUiModel
import woowacourse.movie.model.ReservationResultUiModel
import woowacourse.movie.model.ScreenMovieUiModel
import woowacourse.movie.model.ScreeningMovie
import java.time.format.DateTimeFormatter

fun ScreeningMovie.toScreenMovieUiModel(): ScreenMovieUiModel {
    val pattern = "yyyy.MM.dd"
    val screenDate: String =
        screenDateTimes.first().date.format(DateTimeFormatter.ofPattern(pattern))
    val runningTime = movie.runningTime.time.inWholeMinutes
    return ScreenMovieUiModel(
        id = id,
        title = movie.title,
        screenDate = "러닝타임: ${runningTime}분",
        runningTime = "상영일: $screenDate",
    )
}

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

fun MovieReservation.toUiModel(): ReservationResultUiModel {
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

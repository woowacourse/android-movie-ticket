package woowacourse.movie.moviereservation

import woowacourse.movie.model.HeadCount
import woowacourse.movie.model.ScreeningMovie
import woowacourse.movie.moviereservation.uimodel.HeadCountUiModel
import woowacourse.movie.moviereservation.uimodel.MovieReservationUiModel
import woowacourse.movie.moviereservation.uimodel.ScreeningDateTimeUiModel

fun ScreeningMovie.toMovieReservationUiModel(): MovieReservationUiModel =
    MovieReservationUiModel(
        id,
        movie.title,
        screenDateTimes.first().date,
        screenDateTimes.last().date,
        movie.description,
        movie.runningTime.time,
    )

fun HeadCount.toHeadCountUiModel(): HeadCountUiModel = HeadCountUiModel(count)

fun HeadCountUiModel.toHeadCount(): HeadCount = HeadCount(count.toInt())

fun ScreeningMovie.toScreeningDateTimeUiModel(): List<ScreeningDateTimeUiModel> =
    screenDateTimes.map {
        ScreeningDateTimeUiModel(
            it.date,
            it.times,
        )
    }

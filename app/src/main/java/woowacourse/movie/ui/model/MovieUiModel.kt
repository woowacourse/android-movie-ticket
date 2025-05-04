package woowacourse.movie.ui.model

import androidx.annotation.DrawableRes
import woowacourse.movie.domain.model.movie.Movie
import woowacourse.movie.domain.model.movie.RunningMinute
import woowacourse.movie.domain.model.movie.ScreeningDate
import java.io.Serializable
import java.time.LocalDate

data class MovieUiModel(
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningMinute: Int,
    @DrawableRes val poster: Int,
) : MovieItem, Serializable

fun Movie.toMovieUiModel() =
    MovieUiModel(
        title,
        screeningDate.startDate,
        screeningDate.endDate,
        runningMinute.value,
        poster.toInt(),
    )

fun MovieUiModel.toMovie() =
    Movie(
        title,
        ScreeningDate(startDate, endDate),
        RunningMinute(runningMinute),
        poster.toString(),
    )

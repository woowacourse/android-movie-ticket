package woowacourse.movie.presentation.uimodel

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.presentation.utils.dateToString
import java.time.LocalDate

data class MovieUiModel(
    val movieId: Int,
    val posterName: String,
    val title: String,
    val screeningDates: List<LocalDate>,
    val screeningStartDate: String,
    val screeningEndDate: String,
    val runningTime: Int,
    val summary: String,
) {
    companion object {
        fun fromMovie(movie: Movie): MovieUiModel {
            return MovieUiModel(
                movieId = movie.movieId,
                posterName = movie.posterName,
                title = movie.title,
                screeningDates = movie.screeningDates,
                screeningStartDate = movie.screeningDates.minOrNull()?.dateToString() ?: "",
                screeningEndDate = movie.screeningDates.maxOrNull()?.dateToString() ?: "",
                runningTime = movie.runningTime,
                summary = movie.summary,
            )
        }
    }
}

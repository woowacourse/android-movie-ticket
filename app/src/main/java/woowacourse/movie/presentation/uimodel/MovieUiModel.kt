package woowacourse.movie.presentation.uimodel

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.presentation.utils.dateToString

data class MovieUiModel(
    val movieId: Int,
    val posterName: String,
    val title: String,
    val screeningDate: String,
    val runningTime: Int,
    val summary: String,
) {
    companion object {
        fun fromMovie(movie: Movie): MovieUiModel {
            return MovieUiModel(
                movieId = movie.movieId,
                posterName = movie.posterName,
                title = movie.title,
                screeningDate = movie.screeningDate.dateToString(),
                runningTime = movie.runningTime,
                summary = movie.summary,
            )
        }
    }
}

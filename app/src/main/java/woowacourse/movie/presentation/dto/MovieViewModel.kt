package woowacourse.movie.presentation.dto

import woowacourse.movie.domain.model.Movie
import java.time.format.DateTimeFormatter

data class MovieViewModel(
    val movieId: Int,
    val posterName: String,
    val title: String,
    val screeningDate: String,
    val runningTime: Int,
    val summary: String,
) {
    companion object {
        fun fromMovie(movie: Movie): MovieViewModel {
            return MovieViewModel(
                movieId = movie.movieId,
                posterName = movie.posterName,
                title = movie.title,
                screeningDate = movie.screeningDateToString(),
                runningTime = movie.runningTime,
                summary = movie.summary,
            )
        }

        private fun Movie.screeningDateToString(): String {
            return this.screeningDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
        }
    }
}

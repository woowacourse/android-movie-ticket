package woowacourse.movie.ui

import woowacourse.movie.R
import woowacourse.movie.domain.Movie

data class MovieUiModel(
    val poster: Int,
    val title: String,
    val startDate: String,
    val endDate: String,
    val runningTime: String
) {
    companion object {
        private val moviePosterMap = mapOf(
            "harry_potter" to R.drawable.harry_potter_poster,
        )

        fun fromDomain(movie: Movie): MovieUiModel {
            val posterResId = moviePosterMap[movie.title] ?: R.drawable.harry_potter_poster

            return MovieUiModel(
                poster = posterResId,
                title = movie.title,
                startDate = movie.startDate.toString(),
                endDate = movie.endDate.toString(),
                runningTime = "${movie.runningTime.toMinutes()}"
            )
        }
    }
}

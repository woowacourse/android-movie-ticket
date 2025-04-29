package woowacourse.movie.ui

import woowacourse.movie.R
import woowacourse.movie.domain.Movie

data class MovieUiModel(
    val poster: Int,
    val title: String,
    val startDate: String,
    val endDate: String,
    val runningTime: String,
) {
    companion object {
        private val moviePosterMap =
            mapOf(
                "해리 포터와 마법사의 돌" to R.drawable.harry_potter_and_the_philosophers_stone,
                "해리 포터와 비밀의 방" to R.drawable.harry_potter_and_the_secret_chamber,
                "해리 포터와 아즈카반의 죄수" to R.drawable.harry_potter_and_the_prisoner_of_azkaban,
                "해리 포터와 불의 잔" to R.drawable.harry_potter_and_the_goblet_of_fire,
            )

        fun fromDomain(movie: Movie): MovieUiModel {
            val posterResId = moviePosterMap[movie.title] ?: R.drawable.harry_potter_and_the_philosophers_stone

            return MovieUiModel(
                poster = posterResId,
                title = movie.title,
                startDate = movie.startDate.toString(),
                endDate = movie.endDate.toString(),
                runningTime = "${movie.runningTime.toMinutes()}분",
            )
        }
    }
}

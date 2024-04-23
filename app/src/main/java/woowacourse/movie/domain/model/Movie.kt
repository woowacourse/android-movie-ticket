package woowacourse.movie.domain.model

import java.time.LocalDate

data class Movie(
    val movieId: Int,
    val title: String,
    val imageName: String?,
    val screeningDate: LocalDate,
    val runningTime: Int,
    val description: String,
) {
    companion object {
        const val DEFAULT_MOVIE_PRICE = 13_000
    }
}

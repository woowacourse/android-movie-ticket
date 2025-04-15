package woowacourse.movie.model

import woowacourse.movie.R
import java.time.LocalDate

data class Movie(
    val title: String,
    val poster: Int,
    val screeningDate: LocalDate,
    val runningTime: Int,
) {
    companion object {
        val values: List<Movie> =
            listOf(Movie("라라랜드", R.drawable.lalaland, LocalDate.of(2025, 4, 15), 120))
    }
}

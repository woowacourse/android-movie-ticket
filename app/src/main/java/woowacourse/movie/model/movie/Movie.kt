package woowacourse.movie.model.movie

import woowacourse.movie.R
import java.io.Serializable
import java.time.LocalDate

data class Movie(
    val id: Long,
    val title: String,
    val poster: Int,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
) : Serializable {
    companion object {
        val values: List<Movie> =
            listOf(
                Movie(
                    1,
                    "라라랜드",
                    R.drawable.lalaland,
                    LocalDate.of(2025, 4, 1),
                    LocalDate.of(2025, 5, 30),
                    120,
                ),
            )
    }
}

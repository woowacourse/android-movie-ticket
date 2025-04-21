package woowacourse.movie.domain.model

import java.time.LocalDate

data class Movie(
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
) {
    companion object {
        val movies: List<Movie> =
            listOf(
                Movie(
                    title = "해리 포터와 마법사의 돌",
                    startDate = LocalDate.of(2025, 4, 1),
                    endDate = LocalDate.of(2025, 4, 25),
                    runningTime = 152,
                ),
            )
    }
}

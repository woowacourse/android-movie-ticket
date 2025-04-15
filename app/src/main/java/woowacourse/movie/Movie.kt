package woowacourse.movie

import java.time.LocalDate

data class Movie(
    val title: String,
    val date: LocalDate,
    val runningTime: Int,
    val poster: Int,
)

val movies: List<Movie> =
    listOf(
        Movie("해리 포터와 마법사의 돌", LocalDate.of(2025, 4, 1), 152, 0),
    )

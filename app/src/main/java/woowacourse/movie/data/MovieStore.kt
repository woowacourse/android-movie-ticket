package woowacourse.movie.data

import woowacourse.movie.domain.model.booking.ScreeningDate
import woowacourse.movie.domain.model.movies.Movie
import java.time.LocalDate

class MovieStore {
    private val movieTitles =
        listOf(
            "해리 포터와 마법사의 돌",
            "해리 포터와 비밀의 방",
            "해리 포터와 아즈카반의 죄수",
            "해리 포터와 불의 잔",
            "해리 포터와 불사조 기사단",
            "해리 포터와 혼혈 왕자",
            "해리 포터와 죽음의 성물 1부",
            "해리 포터와 죽음의 성물 2부",
        )

    private val screeningDate =
        ScreeningDate(
            LocalDate.of(2025, 5, 1),
            LocalDate.of(2025, 5, 25),
        )

    private val movies: Map<Int, Movie> =
        (0 until 10_000).associateWith { index ->
            val titleIndex = index % movieTitles.size
            Movie(
                id = index,
                title = movieTitles[titleIndex],
                posterResource = "harry_potter_${titleIndex + 1}",
                releaseDate = screeningDate,
                runningTime = 152,
            )
        }

    operator fun get(id: Int): Movie = movies[id] ?: throw IllegalStateException(NOT_FOUND_MOVIE)

    fun getAll(): List<Movie> = movies.values.toList()

    companion object {
        private const val NOT_FOUND_MOVIE = "영화를 찾을 수 없습니다."
    }
}

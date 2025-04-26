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

    private val movies: List<Movie> by lazy {
        val screeningDate =
            ScreeningDate(
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 5, 25),
            )

        List(10_000) { index ->
            val titleIndex = index % movieTitles.size
            Movie(
                id = index,
                title = movieTitles[titleIndex],
                posterResource = "harry_potter_${titleIndex + 1}",
                releaseDate = screeningDate,
                runningTime = 152,
            )
        }
    }

    operator fun get(index: Int): Movie = movies[index]

    fun getAll(): List<Movie> = movies.toList()
}

package woowacourse.movie

import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.RunningTime
import woowacourse.movie.domain.ScreeningDate
import java.time.LocalDate

object DefaultMovies {
    private val startDate: LocalDate = LocalDate.of(2025, 4, 1)
    private val endDate: LocalDate = LocalDate.of(2025, 4, 25)

    val movies =
        listOf(
            Movie(
                "해리포터와 마법사의 돌",
                ScreeningDate(startDate, endDate),
                RunningTime(152),
                R.drawable.harrypotter,
            ),
        )
}

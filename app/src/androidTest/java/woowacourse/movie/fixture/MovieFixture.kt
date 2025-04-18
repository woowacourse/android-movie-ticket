package woowacourse.movie.fixture

import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.RunningTime
import woowacourse.movie.domain.ScreeningDate
import java.time.LocalDate

val START_DATE: LocalDate = LocalDate.of(2025, 4, 30)
val END_DATE: LocalDate = LocalDate.of(2025, 5, 4)

fun createMovie(movieName: String): Movie =
    Movie(
        title = movieName,
        screeningDate =
            ScreeningDate(
                START_DATE,
                END_DATE,
            ),
        runningTime =
            RunningTime(
                152,
            ),
        imageUrl = R.drawable.harrypotter,
    )

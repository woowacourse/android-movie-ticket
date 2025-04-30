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
        imageUrl = R.drawable.harry_potter_01,
    )

const val MOVIE_NAME: String = "해리포터"
const val SCREENING_PERIOD: String = "상영일: 2025.04.30 ~ 2025.05.04"
const val RUNNING_TIME: String = "러닝타임: 152분"
const val FIRST_DATE: String = "2025-04-30"
const val FIRST_TIME: String = "10:00"

package woowacourse.movie.fixture

import woowacourse.movie.domain.Movie
import java.time.LocalDate

val HARRY_POTTER_01 =
    Movie(
        title = "해리 포터와 마법사의 돌",
        startDate = LocalDate.of(2025, 4, 1),
        endDate = LocalDate.of(2025, 4, 30),
        runningTime = 152,
    )

val HARRY_POTTER_02 =
    Movie(
        title = "해리 포터와 비밀의 방",
        startDate = LocalDate.of(2025, 4, 1),
        endDate = LocalDate.of(2025, 4, 30),
        runningTime = 161,
    )

val HARRY_POTTER_03 =
    Movie(
        title = "해리 포터와 아즈카반의 죄수",
        startDate = LocalDate.of(2025, 4, 1),
        endDate = LocalDate.of(2025, 4, 30),
        runningTime = 141,
    )

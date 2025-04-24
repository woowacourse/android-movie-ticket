package woowacourse.movie.domain

import java.time.LocalDate

val APRIL_THIRTIETH: LocalDate = LocalDate.of(2025, 4, 30)
val MAY_FIRST: LocalDate = LocalDate.of(2025, 5, 1)
val MAY_SECOND: LocalDate = LocalDate.of(2025, 5, 2)
val MAY_THIRD: LocalDate = LocalDate.of(2025, 5, 3)

val HARRY_POTTER_MOVIE =
    Movie(
        title = "해리포터",
        screeningDate =
            ScreeningDate(
                APRIL_THIRTIETH,
                MAY_THIRD,
            ),
        runningTime =
            RunningTime(
                152,
            ),
        imageUrl = 0,
    )

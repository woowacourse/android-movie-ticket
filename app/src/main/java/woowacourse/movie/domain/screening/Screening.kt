package woowacourse.movie.domain.screening

import java.time.LocalDate

data class Screening(
    val movie: Movie,
    val schedule: ScreeningSchedule = ScreeningSchedule(
        listOf(
            DailySchedule(
                LocalDate.of(
                    2024,
                    3,
                    1
                )
            )
        )
    )
)

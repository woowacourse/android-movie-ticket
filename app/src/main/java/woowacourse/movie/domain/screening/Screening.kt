package woowacourse.movie.domain.screening

import java.time.LocalDate

data class Screening(
    val movie: Movie,
    val schedule: Schedule = Schedule(LocalDate.of(2024, 3, 1)),
)

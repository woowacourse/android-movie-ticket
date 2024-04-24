package woowacourse.movie.model.screening

import woowacourse.movie.model.Movie
import java.time.LocalDate

data class Screening(
    val movie: Movie,
    val schedule: Schedule = Schedule(LocalDate.of(2024, 3, 1)),
)

package woowacourse.movie.model.screening

import woowacourse.movie.model.Movie
import java.io.Serializable
import java.time.LocalDate

class Screening(
    val movie: Movie,
    val schedule: Schedule = Schedule(LocalDate.of(2024, 3, 1)),
) : Serializable

package woowacourse.movie.model

import java.time.LocalDate

data class ReservedMovie(
    val title: String,
    val screeningDate: LocalDate,
)

package woowacourse.movie.model

import java.io.Serializable
import java.time.LocalDate

data class ReservedMovie(
    val title: String,
    val screeningDate: LocalDate,
) : Serializable

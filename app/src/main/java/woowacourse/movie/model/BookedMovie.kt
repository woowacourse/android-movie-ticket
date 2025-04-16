package woowacourse.movie.model

import java.io.Serializable
import java.time.LocalDate

data class BookedMovie(
    val title: String,
    val screeningDate: LocalDate,
) : Serializable

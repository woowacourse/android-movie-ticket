package woowacourse.movie.domain.model

import java.time.LocalDate

data class Ticket(
    val title: String,
    val screeningDate: LocalDate,
    val count: Int,
    val price: Int,
)

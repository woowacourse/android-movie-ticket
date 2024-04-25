package woowacourse.movie.ticket.model

import java.io.Serializable
import java.time.LocalDate

data class Ticket(
    val title: String,
    val screeningDate: LocalDate,
    val price: Int,
    val id: Long,
) : Serializable

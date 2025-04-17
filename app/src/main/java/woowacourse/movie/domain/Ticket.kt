package woowacourse.movie.domain

import java.io.Serializable
import java.time.LocalDateTime

data class Ticket(
    val title: String,
    val date: LocalDateTime,
    val personnel: Int,
) : Serializable {
    companion object {
        const val PRICE = 13_000
    }
}

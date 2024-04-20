package woowacourse.movie.model

import java.io.Serializable
import java.time.LocalDateTime

data class Ticket(
    val title: String,
    val screeningDate: LocalDateTime,
    val price: Int,
) : Serializable {
    companion object {
        const val KEY_NAME_TICKET = "ticket"
    }
}

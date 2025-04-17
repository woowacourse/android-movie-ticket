package woowacourse.movie.domain

import java.io.Serializable
import java.time.LocalDateTime

data class Ticket(
    val title: String,
    val date: LocalDateTime,
    val personnel: Int,
) : Serializable {
    constructor() : this(
        "untitled",
        LocalDateTime.of(2025, 1, 1, 1, 1),
        1,
    )

    companion object {
        const val DEFAULT_PRICE = 13_000
        const val CANCEL_DEADLINE = 15
    }
}

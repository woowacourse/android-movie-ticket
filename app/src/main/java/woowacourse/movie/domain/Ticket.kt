package woowacourse.movie.domain

import java.io.Serializable
import java.time.LocalDateTime

data class Ticket(
    val title: String,
    val date: LocalDateTime,
    val personnel: Int,
) : Serializable {
    constructor() : this(
        "untitle",
        LocalDateTime.of(2025, 1, 1, 1, 1),
        1,
    )

    companion object {
        const val DEFAULT_PRICE = 13_000
    }
}

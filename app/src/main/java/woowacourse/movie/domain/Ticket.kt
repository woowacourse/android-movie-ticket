package woowacourse.movie.domain

import java.io.Serializable
import java.time.LocalDateTime

data class Ticket(
    val title: String,
    val date: LocalDateTime,
    val personnel: Int,
) : Serializable {
    constructor() : this(
        UNTITLED_MOVIE_TITLE,
        DEFAULT_DATETIME,
        DEFAULT_PERSONNEL,
    )

    companion object {
        const val DEFAULT_PRICE = 13_000
        const val CANCEL_DEADLINE = 15
        private const val UNTITLED_MOVIE_TITLE = "untitled"
        private val DEFAULT_DATETIME = LocalDateTime.of(2025, 1, 1, 1, 1)
        private const val DEFAULT_PERSONNEL = 1
    }
}

package woowacourse.movie.domain

import java.time.LocalDateTime

data class Ticket(
    val id: Long,
    val movie: Movie,
    val schedule: LocalDateTime,
    val seats: List<String>,
    val price: Long,
)

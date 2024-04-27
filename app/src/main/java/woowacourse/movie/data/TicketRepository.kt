package woowacourse.movie.data

import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Ticket
import java.time.LocalDateTime

interface TicketRepository {
    fun findAll(): List<Ticket>

    fun find(id: Long): Ticket?

    fun save(
        movie: Movie,
        schedule: LocalDateTime,
        seats: List<String>,
        price: Long,
    ): Long
}

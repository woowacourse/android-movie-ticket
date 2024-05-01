package woowacourse.movie.model.data

import woowacourse.movie.model.reservation.Ticket
import woowacourse.movie.model.seat.SelectedSeats
import java.time.LocalDateTime

interface TicketRepository {
    fun save(
        movieId: Long,
        screeningDateTime: LocalDateTime,
        selectedSeats: SelectedSeats,
    ): Long

    fun find(id: Long): Ticket

    fun findAll(): List<Ticket>

    fun deleteAll()
}

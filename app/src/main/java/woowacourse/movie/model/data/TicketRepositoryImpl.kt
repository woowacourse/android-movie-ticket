package woowacourse.movie.model.data

import woowacourse.movie.model.reservation.Ticket
import woowacourse.movie.model.seat.SelectedSeats
import java.time.LocalDateTime

object TicketRepositoryImpl : TicketRepository {
    private val tickets = mutableMapOf<Long, Ticket>()
    private const val NOT_EXIST_ID_MESSAGE = "해당하는 아이디의 티켓을 찾을 수 없습니다."

    override fun save(
        movieId: Long,
        screeningDateTime: LocalDateTime,
        selectedSeats: SelectedSeats,
    ): Long {
        val ticket = GenerateTicket.ticket(movieId, screeningDateTime, selectedSeats)
        tickets[ticket.id] = ticket
        return ticket.id
    }

    override fun find(id: Long): Ticket {
        return tickets[id] ?: throw IllegalArgumentException(NOT_EXIST_ID_MESSAGE)
    }

    override fun findAll(): List<Ticket> {
        return tickets.map { it.value }
    }

    override fun deleteAll() {
        tickets.clear()
    }
}

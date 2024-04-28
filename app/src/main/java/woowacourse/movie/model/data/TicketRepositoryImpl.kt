package woowacourse.movie.model.data

import woowacourse.movie.model.reservation.Ticket

object TicketRepositoryImpl : TicketRepository {
    private var id: Long = 0
    private val tickets = mutableMapOf<Long, Ticket>()
    private const val NOT_EXIST_ID_MESSAGE = "해당하는 아이디의 티켓을 찾을 수 없습니다."

    override fun save(ticket: Ticket): Long {
        tickets[id] = ticket.copy(id = id)
        return id++
    }

    override fun find(id: Long): Ticket {
        println(tickets.size)
        return tickets[id] ?: throw IllegalArgumentException(NOT_EXIST_ID_MESSAGE)
    }

    override fun findAll(): List<Ticket> {
        return tickets.map { it.value }
    }

    override fun deleteAll() {
        tickets.clear()
    }
}

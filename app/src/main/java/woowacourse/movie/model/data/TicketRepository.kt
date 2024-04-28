package woowacourse.movie.model.data

import woowacourse.movie.model.reservation.Ticket

interface TicketRepository {
    fun save(ticket: Ticket): Long

    fun find(id: Long): Ticket

    fun findAll(): List<Ticket>

    fun deleteAll()
}

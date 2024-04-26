package woowacourse.movie.model.data

import woowacourse.movie.model.movie.UserTicket

object UserTicketsImpl : UserTickets {
    private const val EXCEPTION_INVALID_ID = "Movie not found with id: %d"
    private var id: Long = 0
    private val tickets = mutableMapOf<Long, UserTicket>()

    override fun save(userTicket: UserTicket): Long {
        tickets[id] = userTicket.copy(id = id)
        return id++
    }

    override fun find(id: Long): UserTicket {
        return tickets[id] ?: throw NoSuchElementException(invalidIdMessage(id))
    }

    override fun deleteAll() {
        tickets.clear()
    }

    private fun invalidIdMessage(id: Long) = EXCEPTION_INVALID_ID.format(id)
}

package woowacourse.movie.domain

import java.io.Serializable

class Tickets(private val tickets: List<Ticket>) : Serializable {
    val count get() = tickets.size

    init {
        require(tickets.size >= MINIMUM_COUNT) { INVALID_COUNT }
    }

    fun totalPrice() = tickets.sumOf { it.price }

    fun canMinus(): Boolean = count > MINIMUM_COUNT

    fun add(ticket: Ticket): Tickets = Tickets(tickets + ticket)

    fun remove(ticket: Ticket): Tickets = removeLast(ticket)

    private fun removeLast(ticket: Ticket): Tickets {
        val removeIndex = tickets.lastIndexOf(ticket)
        val mutableTickets = tickets.toMutableList()
        mutableTickets.removeAt(removeIndex)
        return Tickets(mutableTickets)
    }

    companion object {
        private const val INVALID_COUNT = "예약 개수는 1보다 같거나 커야 합니다."
        private const val MINIMUM_COUNT = 1
    }
}

package woowacourse.movie.model

import java.io.Serializable

class Ticket(count: Int = DEFAULT_TICKET_COUNT) : Serializable {
    var count: Int = count
        private set

    private fun restoreCount(recordOfCount: Int) {
        count = recordOfCount
    }

    fun restoreTicket(count: Int): Ticket {
        restoreCount(count)
        return this
    }

    fun increaseCount(): ChangeTicketCountResult {
        if (count >= MAX_TICKET_COUNT) return Failure
        count++
        return Success
    }

    fun decreaseCount(): ChangeTicketCountResult {
        if (count <= MIN_TICKET_COUNT) return Failure
        count--
        return Success
    }

    fun calculatePrice(): Int = count * PRICE

    companion object {
        private const val PRICE = 13_000
        private const val DEFAULT_TICKET_COUNT = 1
        private const val MAX_TICKET_COUNT = 20
        private const val MIN_TICKET_COUNT = 1
    }
}

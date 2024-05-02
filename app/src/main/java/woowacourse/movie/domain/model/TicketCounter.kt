package woowacourse.movie.domain.model

class TicketCounter {
    var ticketCount = MIN_TICKET_COUNT
        private set

    fun minusTicketCount() {
        ticketCount = ticketCount.minus(1).coerceAtLeast(MIN_TICKET_COUNT)
    }

    fun plusTicketCount() {
        ticketCount++
    }

    fun initTicketCount(count: Int) {
        ticketCount = count
    }

    companion object {
        const val MIN_TICKET_COUNT = 1
    }
}

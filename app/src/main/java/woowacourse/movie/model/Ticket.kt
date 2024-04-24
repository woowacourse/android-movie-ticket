package woowacourse.movie.model

class Ticket(ticketCount: Int = DEFAULT_TICKET_COUNT) {
    var count: Int = ticketCount
        private set

    fun increaseCount(): ChangeTicketCountResult {
        if (count >= MAX_TICKET_COUNT) return OutOfRange
        count++
        return InRange
    }

    fun decreaseCount(): ChangeTicketCountResult {
        if (count <= MIN_TICKET_COUNT) return OutOfRange
        count--
        return InRange
    }

    fun calculatePrice(): Int = count * PRICE

    companion object {
        private const val PRICE = 13_000
        private const val DEFAULT_TICKET_COUNT = 1
        private const val MAX_TICKET_COUNT = 100
        private const val MIN_TICKET_COUNT = 1
    }
}

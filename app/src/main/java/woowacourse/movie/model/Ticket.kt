package woowacourse.movie.model

class Ticket(ticketCount: Int = DEFAULT_TICKET_COUNT) {
    private var _count: Int = ticketCount
    val count: Int
        get() = _count

    fun increaseCount(): ChangeTicketCountResult {
        if (count >= MAX_TICKET_COUNT) return OutOfRange
        _count++
        return InRange
    }

    fun decreaseCount(): ChangeTicketCountResult {
        if (count <= MIN_TICKET_COUNT) return OutOfRange
        _count--
        return InRange
    }

    fun calculatePrice(): Int = _count * PRICE

    companion object {
        private const val PRICE = 13_000
        private const val DEFAULT_TICKET_COUNT = 1
        private const val MAX_TICKET_COUNT = 100
        private const val MIN_TICKET_COUNT = 1
    }
}

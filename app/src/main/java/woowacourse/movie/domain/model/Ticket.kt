package woowacourse.movie.domain.model

data class Ticket(val count: Int) {
    fun increase(): Ticket = update(count + DEFAULT_PLUS_QUANTITY)

    fun decrease(): Ticket = update(count - DEFAULT_MINUS_QUANTITY)

    private fun update(quantity: Int): Ticket = copy(count = quantity)

    fun isInvalidCount(): Boolean = count < MIN_TICKET_COUNT || count > MAX_TICKET_COUNT

    companion object {
        const val MAX_TICKET_COUNT = 10
        const val MIN_TICKET_COUNT = 1

        private const val DEFAULT_PLUS_QUANTITY = 1
        private const val DEFAULT_MINUS_QUANTITY = 1
    }
}

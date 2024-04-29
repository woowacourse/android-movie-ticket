package woowacourse.movie.presentation.model

data class Ticket(val count: Int) {
    fun increase(quantity: Int): Ticket = copy(count = count + quantity)

    fun decrease(quantity: Int): Ticket = copy(count = count - quantity)

    fun isInvalidCount(): Boolean {
        return count < MIN_TICKET_COUNT || count > MAX_TICKET_COUNT
    }

    companion object {
        const val MAX_TICKET_COUNT = 10
        const val MIN_TICKET_COUNT = 1
    }
}

package woowacourse.movie.domain.model

class TicketCount(
    initialCount: Int = INITIAL_TICKET_COUNT,
) {
    var value: Int = initialCount
        private set

    fun increase(count: Int) {
        value += count
    }

    fun decrease(count: Int) {
        if (value - count > INITIAL_TICKET_COUNT) {
            value -= count
        } else {
            value = INITIAL_TICKET_COUNT
        }
    }

    companion object {
        private const val INITIAL_TICKET_COUNT = 1
    }
}

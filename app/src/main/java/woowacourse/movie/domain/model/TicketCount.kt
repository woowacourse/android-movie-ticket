package woowacourse.movie.domain.model

class TicketCount(
    initialCount: Int = INITIAL_TICKET_COUNT,
) {
    var value: Int = initialCount
        private set

    operator fun times(other: Int): Int = value * other

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
        const val INITIAL_TICKET_COUNT = 1
    }
}

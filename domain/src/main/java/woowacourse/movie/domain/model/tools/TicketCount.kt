package woowacourse.movie.domain.model.tools

@JvmInline
value class TicketCount(val value: Int = MIN_TICKET_COUNT) {
    init {
        require(value >= MIN_TICKET_COUNT)
    }

    fun plus(): TicketCount = TicketCount(value + TICKET_UNIT)

    fun minus(): TicketCount {
        if (value == MIN_TICKET_COUNT) return this
        return TicketCount(value - TICKET_UNIT)
    }

    companion object {
        private const val MIN_TICKET_COUNT = 1
        private const val TICKET_UNIT = 1
    }
}

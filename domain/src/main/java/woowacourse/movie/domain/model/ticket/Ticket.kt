package woowacourse.movie.domain.model.ticket

typealias DomainTicket = Ticket

@JvmInline
value class Ticket(val count: Int = MIN_TICKET_COUNT) {
    init {
        require(count in MIN_TICKET_COUNT..MAX_TICKET_COUNT) { INVALID_TICKET_COUNT_EXCEPTION_MESSAGE }
    }

    operator fun minus(operand: Int): Ticket =
        Ticket((count - operand).coerceAtLeast(MIN_TICKET_COUNT))

    operator fun plus(operand: Int): Ticket =
        Ticket((count + operand).coerceAtMost(MAX_TICKET_COUNT))

    companion object {
        private const val MIN_TICKET_COUNT = 1
        private const val MAX_TICKET_COUNT = 100

        private const val INVALID_TICKET_COUNT_EXCEPTION_MESSAGE = "티켓 개수는 최소 1장 이상이어야 합니다."
    }
}

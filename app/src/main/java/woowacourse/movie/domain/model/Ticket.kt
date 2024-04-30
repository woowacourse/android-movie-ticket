package woowacourse.movie.domain.model

data class Ticket(val count: Int) {
    init {
        require(count in MIN_TICKET_COUNT..MAX_TICKET_COUNT) { "티켓 수량은 $MIN_TICKET_COUNT~$MAX_TICKET_COUNT 사이어야 합니다." }
    }

    fun increase(): Ticket = copy(count = count + DEFAULT_PLUS_QUANTITY)

    fun decrease(): Ticket = copy(count = count - DEFAULT_MINUS_QUANTITY)

    companion object {
        const val MAX_TICKET_COUNT = 10
        const val MIN_TICKET_COUNT = 1

        private const val DEFAULT_PLUS_QUANTITY = 1
        private const val DEFAULT_MINUS_QUANTITY = 1
    }
}

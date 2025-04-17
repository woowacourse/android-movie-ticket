package woowacourse.movie.model

@JvmInline
value class TicketCount(
    val value: Int = 1,
) {
    init {
        require(value >= TICKET_MINIMUM_COUNT) { TICKET_MINIMUM_ERROR_MESSAGE }
    }

    operator fun plus(other: Int): TicketCount = TicketCount(value + other)

    operator fun minus(other: Int): TicketCount = TicketCount(value - other)

    companion object {
        private const val TICKET_MINIMUM_COUNT = 1
        private const val TICKET_MINIMUM_ERROR_MESSAGE = "최소 1명은 선택해야 합니다."
    }
}

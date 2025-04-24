package woowacourse.movie.model.ticket

@JvmInline
value class TicketCount private constructor(
    val value: Int,
) {
    fun increase() = create(value + 1)

    fun decrease() = create(value - 1)

    override fun toString(): String = value.toString()

    companion object {
        private const val MIN_TICKET_COUNT = 1

        fun createDefault(): TicketCount = TicketCount(MIN_TICKET_COUNT)

        fun create(value: Int = MIN_TICKET_COUNT): TicketCountResult =
            if (value >= MIN_TICKET_COUNT) {
                TicketCountResult.Success(TicketCount(value))
            } else {
                TicketCountResult.Error("티켓 수량($value)은 최소 티켓 수량($MIN_TICKET_COUNT) 이상이어야 합니다")
            }
    }
}

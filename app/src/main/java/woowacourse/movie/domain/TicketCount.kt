package woowacourse.movie.domain

@JvmInline
value class TicketCount private constructor(
    val value: Int,
) {
    fun increase() = create(value + 1)

    fun decrease() = if (value > MIN_TICKET_COUNT) create(value - 1) else this

    override fun toString(): String = value.toString()

    companion object {
        private const val MIN_TICKET_COUNT = 1

        fun create(value: Int = MIN_TICKET_COUNT): TicketCount {
            require(value >= MIN_TICKET_COUNT) {
                "티켓 수량은 $MIN_TICKET_COUNT 이상이어야 합니다"
            }
            return TicketCount(value)
        }
    }
}

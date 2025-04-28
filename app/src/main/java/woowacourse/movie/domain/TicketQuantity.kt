package woowacourse.movie.domain

@JvmInline
value class TicketQuantity(
    val value: Int,
) {
    init {
        require(value >= 0) { "티켓 수는 음수가 될 수 없습니다." }
    }

    fun increase(): TicketQuantity = TicketQuantity(value + 1)

    fun decrease(): TicketQuantity = if (value > 0) TicketQuantity(value - 1) else this
}

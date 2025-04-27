package woowacourse.movie.model.ticket

@JvmInline
value class TicketPrice(
    val value: Int,
) {
    fun plusPrice(plusAmount: TicketPrice): TicketPrice = TicketPrice(value + plusAmount.value)

    fun minusPrice(minusAmount: TicketPrice): TicketPrice = TicketPrice(value - minusAmount.value)
}

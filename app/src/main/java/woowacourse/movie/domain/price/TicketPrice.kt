package woowacourse.movie.domain.price

@JvmInline
value class TicketPrice(val value: Int) {

    init {
        negativeTicketPrice()
    }

    private fun negativeTicketPrice() {
        require(value >= 0) { INAPPROPRIATE_TICKET_TYPE_PRICE }
    }

    operator fun plus(other: Int): TicketPrice = TicketPrice(value + other)

    operator fun minus(other: Int): TicketPrice = TicketPrice(value - other)

    operator fun div(other: Int): TicketPrice = TicketPrice(value / other)

    operator fun times(other: Int): TicketPrice = TicketPrice(value * other)

    operator fun times(other: Double): TicketPrice = TicketPrice((value * other).toInt())

    operator fun times(other: Float): TicketPrice = TicketPrice((value * other).toInt())

    operator fun times(other: TicketCount): TicketPrice = TicketPrice(value * other.value)

    companion object {
        private const val INAPPROPRIATE_TICKET_TYPE_PRICE = "부적절한 값이 티켓값으로 설정되었습니다."

        const val STANDARD_TICKET_PRICE = 13000
    }
}

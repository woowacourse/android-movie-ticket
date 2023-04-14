package woowacourse.movie.presentation.model

@JvmInline
value class TicketPrice(val amount: Int = DEFAULT_TICKET_PRICE) {
    companion object {
        private const val DEFAULT_TICKET_PRICE = 13_000
    }
}

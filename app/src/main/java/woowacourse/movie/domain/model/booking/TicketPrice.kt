package woowacourse.movie.domain.model.booking

class TicketPrice {
    fun calculate(headCount: Int): Int {
        return TICKET_PRICE * headCount
    }

    companion object {
        private const val TICKET_PRICE = 13_000
    }
}

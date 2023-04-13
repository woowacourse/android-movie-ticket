package woowacourse.movie.domain

class TicketBundle(private val tickets: List<Ticket>) {
    fun calculateTotalPrice(date: String, time: String): Int = tickets.sumOf {
        it.getTicketPrice(date, time)
    }
}

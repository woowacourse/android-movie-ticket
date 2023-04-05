package woowacourse.movie.domain

class TicketBundle(private val tickets: List<Ticket>) {

    constructor(count: Int) : this(
        mutableListOf<Ticket>().apply {
            repeat(count) {
                add(Ticket())
            }
        }.toList()
    )

    fun calculateTotalPrice(date: String, time: String): Int = tickets.sumOf {
        it.getTicketPrice(date, time)
    }
}

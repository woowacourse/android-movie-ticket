package woowacourse.movie.model.ticketing

import woowacourse.movie.model.Count
import woowacourse.movie.model.screening.Movie

class Tickets(
    private val count: Count,
    val movie: Movie,
) {
    val totalPrice: Int
        get() = count.value * TICKET_PRICE

    companion object {
        private const val TICKET_PRICE = 13000
    }
}

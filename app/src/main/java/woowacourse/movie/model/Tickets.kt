package woowacourse.movie.model

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

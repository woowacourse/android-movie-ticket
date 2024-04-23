package woowacourse.movie.model

class Tickets(private val count: Count, private val price: Int = DEFAULT_TICKET_PRICE) {
    val totalPrice: Int
        get() = count.value * price

    companion object {
        private const val DEFAULT_TICKET_PRICE = 13000
    }
}

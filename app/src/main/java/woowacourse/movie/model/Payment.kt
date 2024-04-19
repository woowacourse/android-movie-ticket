package woowacourse.movie.model

class Payment {
    fun price(count: Int) = count * TICKET_PRICE

    companion object {
        const val TICKET_PRICE: Int = 13_000
    }
}

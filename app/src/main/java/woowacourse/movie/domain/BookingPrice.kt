package woowacourse.movie.domain

@JvmInline
value class BookingPrice private constructor(
    val price: Int
) {
    companion object {
        private const val TICKET_PRICE = 13000

        fun of(count: Int): BookingPrice = BookingPrice(count * TICKET_PRICE)
    }
}

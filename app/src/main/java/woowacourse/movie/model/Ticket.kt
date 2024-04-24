package woowacourse.movie.model

class Ticket(
    val reservationCount: ReservationCount,
    private val price: Int = DEFAULT_PRICE,
) {
    fun amount(): Int = price * reservationCount.count

    companion object {
        private const val DEFAULT_PRICE = 13000
    }
}

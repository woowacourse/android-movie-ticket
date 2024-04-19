package woowacourse.movie.domain.model

class MovieTicket(
    val movieTitle: String,
    val screeningDate: String,
    private var _reservationCount: Int = MIN_RESERVATION_COUNT,
) {
    val reservationCount: Int
        get() = _reservationCount

    fun plusCount() {
        if (_reservationCount < MAX_RESERVATION_COUNT) {
            _reservationCount++
        }
    }

    fun minusCount() {
        if (_reservationCount > MIN_RESERVATION_COUNT) {
            _reservationCount--
        }
    }

    fun totalPrice() = _reservationCount * PRICE_PER_TICKET

    companion object {
        const val MIN_RESERVATION_COUNT = 1
        const val MAX_RESERVATION_COUNT = 20
        const val PRICE_PER_TICKET = 13000
    }
}

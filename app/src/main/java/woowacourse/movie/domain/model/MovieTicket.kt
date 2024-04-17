package woowacourse.movie.domain.model

class MovieTicket(
    val movieTitle: String,
    val screeningDate: String,
) {
    private var _count = MIN_RESERVATION_COUNT
    val count
        get() = _count

    fun plusCount() {
        if (count < MAX_RESERVATION_COUNT) {
            _count++
        }
    }

    fun minusCount() {
        if (count > MIN_RESERVATION_COUNT) {
            _count--
        }
    }

    fun totalPrice() = count * PRICE_PER_TICKET

    companion object {
        const val MIN_RESERVATION_COUNT = 1
        const val MAX_RESERVATION_COUNT = 20
        const val PRICE_PER_TICKET = 13000
    }
}

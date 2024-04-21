package woowacourse.movie.domain.model

class MovieTicket(
    val movieTitle: String,
    val screeningDate: String,
) {
    var count: Int = MIN_RESERVATION_COUNT
        private set

    fun plusCount() {
        if (count < MAX_RESERVATION_COUNT) {
            count++
        }
    }

    fun minusCount() {
        if (count > MIN_RESERVATION_COUNT) {
            count--
        }
    }

    fun totalPrice() = count * PRICE_PER_TICKET

    fun initCount(newCount: Int) {
        count = newCount
    }

    companion object {
        const val MIN_RESERVATION_COUNT = 1
        const val MAX_RESERVATION_COUNT = 20
        const val PRICE_PER_TICKET = 13000
    }
}

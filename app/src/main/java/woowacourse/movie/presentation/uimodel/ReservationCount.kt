package woowacourse.movie.presentation.uimodel

class ReservationCount(initialReservationCount: Int = MIN_RESERVATION_COUNT) {
    var count: Int = initialReservationCount
        private set

    fun updateCount(count: Int) {
        this.count = validateReservationCount(count)
    }

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

    private fun validateReservationCount(count: Int): Int =
        count.coerceIn(
            MIN_RESERVATION_COUNT,
            MAX_RESERVATION_COUNT,
        )

    companion object {
        const val MIN_RESERVATION_COUNT = 1
        const val MAX_RESERVATION_COUNT = 20
    }
}

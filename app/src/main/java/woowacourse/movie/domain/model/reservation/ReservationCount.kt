package woowacourse.movie.domain.model.reservation

class ReservationCount {
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

    fun initCount(newCount: Int) {
        count = newCount
    }

    companion object {
        const val MIN_RESERVATION_COUNT = 1
        const val MAX_RESERVATION_COUNT = 20
    }
}

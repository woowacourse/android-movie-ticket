package woowacourse.movie.domain.model

import java.time.LocalDate

class MovieTicket(
    val id: Int,
    val movieTitle: String,
    val screeningDate: LocalDate,
) {
    var reservationCount: Int = MIN_RESERVATION_COUNT
        private set

    fun updateCount(count: Int) {
        reservationCount = validateReservationCount(count)
    }

    fun plusCount() {
        if (reservationCount < MAX_RESERVATION_COUNT) {
            reservationCount++
        }
    }

    fun minusCount() {
        if (reservationCount > MIN_RESERVATION_COUNT) {
            reservationCount--
        }
    }

    fun totalPrice() = reservationCount * PRICE_PER_TICKET

    private fun validateReservationCount(count: Int): Int = count.coerceIn(MIN_RESERVATION_COUNT, MAX_RESERVATION_COUNT)

    companion object {
        const val MIN_RESERVATION_COUNT = 1
        const val MAX_RESERVATION_COUNT = 20
        const val PRICE_PER_TICKET = 13000
    }
}

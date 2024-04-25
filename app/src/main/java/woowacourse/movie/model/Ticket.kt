package woowacourse.movie.model

import java.time.LocalDateTime

class Ticket(
    val screeningTime: LocalDateTime,
    val reservationCount: ReservationCount,
    private val price: Int = DEFAULT_PRICE,
) {
    fun amount(): Int = price * reservationCount.count

    companion object {
        private const val DEFAULT_PRICE = 13000
    }
}

package woowacourse.movie.domain.model

import java.time.LocalDateTime

class ReservationInfo(
    val title: String,
    val reservationDateTime: LocalDateTime,
    val reservationCount: ReservationCount,
) {
    fun totalPrice(): Int = reservationCount.value * TICKET_PRICE

    companion object {
        private const val TICKET_PRICE = 13_000
    }
}

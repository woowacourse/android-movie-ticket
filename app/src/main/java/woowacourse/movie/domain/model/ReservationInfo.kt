package woowacourse.movie.domain.model

import java.io.Serializable
import java.time.LocalDateTime

class ReservationInfo(
    val title: String,
    val reservationDateTime: LocalDateTime,
    val reservationCount: Int,
) : Serializable {
    fun totalPrice(): Int = reservationCount * TICKET_PRICE

    companion object {
        private const val TICKET_PRICE = 13_000
        const val RESERVATION_MIN_NUMBER = 1
    }
}

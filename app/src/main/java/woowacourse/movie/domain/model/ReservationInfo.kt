package woowacourse.movie.domain.model

import java.io.Serializable
import java.time.LocalDateTime

class ReservationInfo(
    val title: String,
    val reservationDateTime: LocalDateTime,
    val reservationCount: ReservationCount,
) : Serializable {
    fun totalPrice(): Int = reservationCount.count * TICKET_PRICE

    companion object {
        private const val TICKET_PRICE = 13_000
    }
}

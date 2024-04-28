package woowacourse.movie.model.reservation

import woowacourse.movie.model.seat.SelectedSeats
import java.time.LocalDateTime

data class Ticket(
    val movieId: Long,
    val screeningDateTime: LocalDateTime,
    val selectedSeats: SelectedSeats,
    val id: Long = 0,
) {
    fun amount(): ReservationAmount = selectedSeats.amount()
}

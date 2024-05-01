package woowacourse.movie.model.reservation

import woowacourse.movie.model.seat.SelectedSeats
import java.time.LocalDateTime

data class Ticket(
    val id: Long,
    val movieId: Long,
    val screeningDateTime: LocalDateTime,
    val selectedSeats: SelectedSeats,
) {
    fun amount(): ReservationAmount = selectedSeats.amount()
}

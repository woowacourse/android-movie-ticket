package woowacourse.movie.model.reservation

import woowacourse.movie.model.seat.SelectedSeats
import java.time.LocalDateTime

class Ticket(
    val movieId: Long,
    val screeningDateTime: LocalDateTime,
    val selectedSeats: SelectedSeats,
) {
    fun amount(): ReservationAmount = selectedSeats.amount()
}

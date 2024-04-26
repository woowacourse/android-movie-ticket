package woowacourse.movie.model.reservation

import woowacourse.movie.model.seat.SelectedSeats
import java.io.Serializable
import java.time.LocalDateTime

class Ticket(
    val movieId: Long,
    val screeningDateTime: LocalDateTime,
    val selectedSeats: SelectedSeats,
) : Serializable {
    fun amount(): ReservationAmount = selectedSeats.amount()
}

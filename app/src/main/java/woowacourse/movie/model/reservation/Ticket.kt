package woowacourse.movie.model.reservation

import woowacourse.movie.model.seat.SeatRating
import woowacourse.movie.model.seat.SelectedSeats
import java.io.Serializable
import java.time.LocalDateTime

class Ticket(
    val movieId: Long,
    val screeningDateTime: LocalDateTime,
    val selectedSeats: SelectedSeats,
) : Serializable {
    fun amount(): ReservationAmount {
        return selectedSeats.seats.fold(ReservationAmount(0)) { acc, seat ->
            acc + SeatRating.from(seat).amount
        }
    }
}

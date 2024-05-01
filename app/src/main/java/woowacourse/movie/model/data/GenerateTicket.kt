package woowacourse.movie.model.data

import woowacourse.movie.model.reservation.Ticket
import woowacourse.movie.model.seat.SelectedSeats
import java.time.LocalDateTime

object GenerateTicket {
    private var id = 0L

    fun ticket(
        movieId: Long,
        screeningDateTime: LocalDateTime,
        selectedSeats: SelectedSeats,
    ): Ticket {
        return Ticket(
            id = id++,
            movieId = movieId,
            screeningDateTime = screeningDateTime,
            selectedSeats = selectedSeats,
        )
    }
}

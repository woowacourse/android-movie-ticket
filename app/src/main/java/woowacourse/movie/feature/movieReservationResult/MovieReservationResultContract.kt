package woowacourse.movie.feature.movieReservationResult

import woowacourse.movie.feature.model.movie.TicketUiModel
import woowacourse.movie.feature.model.seat.SeatsUiModel
import java.time.LocalDateTime

interface MovieReservationResultContract {
    interface View {
        fun showMovieTitle(title: String)

        fun showMovieDateTime(showtime: LocalDateTime)

        fun showTicketCount(count: Int)

        fun showSelectedSeats(seats: String)

        fun showTotalPrice(price: Int)
    }

    interface Presenter {
        fun onViewCreated(
            ticket: TicketUiModel,
            seats: SeatsUiModel,
        )
    }
}

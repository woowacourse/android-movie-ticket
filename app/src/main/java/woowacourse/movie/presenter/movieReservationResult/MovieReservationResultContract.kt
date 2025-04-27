package woowacourse.movie.presenter.movieReservationResult

import woowacourse.movie.view.model.movie.TicketUiModel
import woowacourse.movie.view.model.theater.TheaterUiModel
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
            theater: TheaterUiModel,
        )
    }
}

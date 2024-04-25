package woowacourse.movie.presenter.finished

import woowacourse.movie.model.Movie
import woowacourse.movie.model.Seats
import woowacourse.movie.model.Ticket

interface ReservationFinishedContract {
    interface View {
        fun showMovieInformation(movie: Movie)

        fun showReservationHistory(
            ticket: Ticket,
            seats: Seats,
        )
    }

    interface Presenter {
        fun loadMovie(movieId: Int)

        fun loadTicket(
            ticket: Ticket,
            seats: Seats,
        )
    }
}

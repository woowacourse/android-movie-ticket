package woowacourse.movie.presenter.finished

import woowacourse.movie.model.movie.Movie
import woowacourse.movie.model.seats.Seats
import woowacourse.movie.model.ticket.Ticket

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

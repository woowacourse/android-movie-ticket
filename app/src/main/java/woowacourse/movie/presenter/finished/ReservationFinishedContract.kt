package woowacourse.movie.presenter.finished

import woowacourse.movie.model.Movie
import woowacourse.movie.model.Ticket

interface ReservationFinishedContract {
    interface View {
        fun showMovieInformation(movie: Movie)

        fun showReservationHistory(
            ticketCount: Int,
            price: Int,
        )
    }

    interface Presenter {
        fun loadMovie(movieId: Int)

        fun loadTicket(ticket: Ticket)
    }
}

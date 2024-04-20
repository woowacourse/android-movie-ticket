package woowacourse.movie.presenter.detail

import woowacourse.movie.model.ChangeTicketCountResult
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Ticket

interface ReservationDetailContract {
    interface View {
        fun showMovieInformation(movie: Movie)

        fun changeNumberOfTickets(ticket: Ticket)

        fun showResultToast()

        fun navigateToFinished(
            movieId: Int,
            ticket: Ticket,
        )
    }

    interface Presenter {
        fun loadMovie(movieId: Int)

        fun increaseTicketCount()

        fun decreaseTicketCount()

        fun initializeReservationButton(movieId: Int)

        fun handleNumberOfTicketsBounds(result: ChangeTicketCountResult)
    }
}

package woowacourse.movie.reservation.detail

import woowacourse.movie.model.Movie
import woowacourse.movie.model.Ticket

interface ReservationDetailContract {
    interface View {
        fun showMovieInformation(movie: Movie)

        fun changeNumberOfTickets(ticket: Ticket)

        fun initializePlusButton(increaseTicketCount: () -> Unit)

        fun initializeMinusButton(decreaseTicketCount: () -> Unit)

        fun initializeReservationButton(movieId: Int)

        fun showResultToast()
    }

    interface Presenter {
        fun detectIncreaseCount()

        fun detectDecreaseCount()

        fun deliverMovie()

        fun deliverReservationHistory()
    }
}

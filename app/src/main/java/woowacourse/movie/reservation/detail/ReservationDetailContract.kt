package woowacourse.movie.reservation.detail

import woowacourse.movie.model.Movie
import woowacourse.movie.model.Ticket

interface ReservationDetailContract {
    fun showMovieInformation(movie: Movie)

    fun changeNumberOfTickets(ticket: Ticket)

    fun initializePlusButton(increaseTicketCount: () -> Unit)

    fun initializeMinusButton(decreaseTicketCount: () -> Unit)

    fun initializeReservationButton(movieId: Int)

    fun showResultToast()
}

package view

import domain.Movie
import domain.Ticket

interface ReservationDetailContract {
    fun showResultToast()

    fun changeNumberOfTickets(ticket: Ticket)

    fun showMovieInformation(movie: Movie)

    fun initializeReservationButton(movieId: Int)
}

package view

import domain.Movie
import domain.Ticket

interface ReservationDetailContract {
    fun showMovieInformation(movie: Movie)

    fun changeNumberOfTickets(ticket: Ticket)

    fun initializePlusButton(increaseTicketCount: () -> Unit)

    fun initializeMinusButton(decreaseTicketCount: () -> Unit)

    fun initializeReservationButton(movieId: Int)

    fun showResultToast()
}

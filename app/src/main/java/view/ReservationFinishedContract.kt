package view

import domain.Movie

interface ReservationFinishedContract {
    fun showMovieInformation(movie: Movie)

    fun showReservationHistory(
        ticketCount: Int,
        price: Int,
    )
}

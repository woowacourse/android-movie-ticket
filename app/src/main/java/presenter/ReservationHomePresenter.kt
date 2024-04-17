package presenter

import domain.Movies
import view.ReservationHomeContract

class ReservationHomePresenter(private val contract: ReservationHomeContract) {
    val movies = Movies.obtainMovies()

    fun deliverMovie(movieId: Int) {
        contract.moveToReservationDetail(movieId)
    }
}

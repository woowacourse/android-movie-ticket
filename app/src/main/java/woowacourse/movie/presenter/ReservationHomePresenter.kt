package woowacourse.movie.presenter

import woowacourse.movie.model.Movies
import woowacourse.movie.view.ReservationHomeContract

class ReservationHomePresenter(private val contract: ReservationHomeContract) {
    val movies = Movies.obtainMovies()

    fun deliverMovie(movieId: Int) {
        contract.moveToReservationDetail(movieId)
    }
}

package woowacourse.movie.home

import woowacourse.movie.model.Movies

class ReservationHomePresenter(
    private val contract: ReservationHomeContract,
) {
    val movies = Movies.obtainMovies()

    fun deliverMovie(movieId: Int) {
        contract.moveToReservationDetail(movieId)
    }
}

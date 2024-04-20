package woowacourse.movie.presenter.home

import woowacourse.movie.model.MovieStorage

class ReservationHomePresenter(
    private val contract: ReservationHomeContract,
) {
    val movies = MovieStorage.obtainMovies()

    fun deliverMovie(movieId: Int) {
        contract.moveToReservationDetail(movieId)
    }
}

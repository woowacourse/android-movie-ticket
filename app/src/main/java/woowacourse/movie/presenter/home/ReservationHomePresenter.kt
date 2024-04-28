package woowacourse.movie.presenter.home

import woowacourse.movie.model.movie.Movie

class ReservationHomePresenter(
    private val view: ReservationHomeContract.View,
) : ReservationHomeContract.Presenter {
    override fun loadMovie(movie: Movie) {
        val movieId = movie.id
        view.navigateToDetail(movieId)
    }
}

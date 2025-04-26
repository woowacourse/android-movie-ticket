package woowacourse.movie.presenter.movies

import woowacourse.movie.model.movie.Movie

class MoviesPresenter(
    private val view: MoviesContracts.View,
) : MoviesContracts.Presenter {
    override fun initView() {
        view.showMovies(Movie.values)
    }

    override fun onReservationRequested(id: Long) {
        val movie: Movie = Movie.values.find { it.id == id } ?: return
        view.showReservationView(movie)
    }

    override fun onClickAdvertisement(url: String) {
        view.showAdvertisement(url)
    }
}

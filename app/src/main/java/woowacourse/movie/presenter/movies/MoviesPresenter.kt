package woowacourse.movie.presenter.movies

import woowacourse.movie.model.movie.Movie

class MoviesPresenter(
    private val view: MoviesContracts.View,
) : MoviesContracts.Presenter {
    override fun initView() {
        view.showMovies(Movie.values)
    }

    override fun createMovie(id: Long) {
        val movie: Movie = Movie.values.find { it.id == id } ?: return
        view.showReservationView(movie)
    }

    override fun clickAdvertisement(url: String) {
        view.showAdvertisement(url)
    }
}

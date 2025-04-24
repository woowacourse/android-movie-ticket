package woowacourse.movie.presentation.movies

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.ScreeningMovies

class MoviesPresenter(
    private val view: MoviesContract.View,
    private val screeningMovies: ScreeningMovies,
) : MoviesContract.Presenter {
    override fun onViewCreated() {
        val screeningMovies = screeningMovies.getData()
        view.showMovies(screeningMovies)
    }

    override fun onMovieClicked(movie: Movie) {
        view.navigateToBooking(movie)
    }
}
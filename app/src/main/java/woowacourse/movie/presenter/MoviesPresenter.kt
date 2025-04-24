package woowacourse.movie.presenter

import woowacourse.movie.Movies
import woowacourse.movie.domain.Movie

class MoviesPresenter(
    private val view: Movies.View
) : Movies.Presenter {
    override fun loadMovies() {
        val movies = woowacourse.movie.domain.Movies.Companion.value
        view.showMovies(movies)
    }

    override fun selectedMovie(movie: Movie) {
        view.navigateToBook(movie)
    }
}

package woowacourse.movie

import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Movies

class MoviesPresenter(
    private val view: woowacourse.movie.Movies.View
) : woowacourse.movie.Movies.Presenter {
    override fun loadMovies() {
        val movies = Movies.value
        view.showMovies(movies)
    }

    override fun selectedMovie(movie: Movie) {
        view.navigateToBook(movie)
    }
}

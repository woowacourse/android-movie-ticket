package woowacourse.movie.presenter

import woowacourse.movie.Movies
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import java.time.LocalDate

class MoviesPresenter(
    private val view: Movies.View
) : Movies.Presenter {
    override fun loadMovies() {
        val movies = woowacourse.movie.domain.Movies.Companion.value
        view.showMovies(movies)
    }

    override fun selectedMovie(movie: Movie) {
        if (movie.screeningPeriod.isEnd(LocalDate.now())) {
            view.showError(R.string.error_over_end_date)
            return
        }
        view.navigateToBook(movie)
    }
}

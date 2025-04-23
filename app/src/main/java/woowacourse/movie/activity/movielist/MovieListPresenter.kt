package woowacourse.movie.activity.movielist

import woowacourse.movie.domain.Movie
import java.time.Duration
import java.time.LocalDate

class MovieListPresenter : MovieListContract.Presenter {
    private var view: MovieListContract.View? = null
    private val movieList: List<Movie> =
        listOf(
            Movie(
                "해리 포터와 마법사의 돌",
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2025, 4, 25),
                Duration.ofMinutes(152),
            ),
        )

    override fun attachView(view: MovieListContract.View) {
        this.view = view
    }

    override fun loadMovies() {
        view?.showMovieList(movieList)
    }

    override fun onMovieClicked(movie: Movie) {
        view?.moveToBooking(movie)
    }
}

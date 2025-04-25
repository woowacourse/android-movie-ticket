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
            Movie(
                "해리 포터와 비밀의 방",
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2025, 4, 28),
                Duration.ofMinutes(162),
            ),
            Movie(
                "해리 포터와 아즈카반의 죄수",
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 5, 31),
                Duration.ofMinutes(141),
            ),
            Movie(
                "해리 포터와 불의 잔",
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 30),
                Duration.ofMinutes(157),
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

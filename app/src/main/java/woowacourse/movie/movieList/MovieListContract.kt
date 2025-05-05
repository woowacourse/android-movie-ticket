package woowacourse.movie.movieList

import woowacourse.movie.uiModel.MovieInfoUIModel
import woowacourse.movie.uiModel.MovieListItem

interface MovieListContract {
    interface View {
        fun showMovies(movies: List<MovieListItem>)

        fun showError()

        fun navigateToBooking(movie: MovieInfoUIModel)
    }

    interface Presenter {
        fun loadMovies()

        fun onMovieSelected(movie: MovieInfoUIModel)
    }
}

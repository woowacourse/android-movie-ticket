package woowacourse.movie.movieList

import woowacourse.movie.uiModel.MovieInfoUIModel

interface MovieListContract {
    interface View {
        fun showMovies(movies: List<MovieInfoUIModel>)

        fun showError()

        fun navigateToBooking(movie: MovieInfoUIModel)
    }

    interface Presenter {
        fun loadMovies()

        fun onMovieSelected(movie: MovieInfoUIModel)
    }
}

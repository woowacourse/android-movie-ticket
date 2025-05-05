package woowacourse.movie.feature.movies.contract

import woowacourse.movie.feature.model.MovieUiModel

interface MoviesContract {
    interface View {
        fun showMovies(movies: List<MovieUiModel>)

        fun navigateToBookingDetail(movie: MovieUiModel)
    }

    interface Presenter {
        fun prepareMovies()

        fun selectMovieForBooking(movie: MovieUiModel)
    }
}

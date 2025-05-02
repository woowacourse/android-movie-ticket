package woowacourse.movie.feature.movieSelection

import woowacourse.movie.feature.model.movie.MovieListItem
import woowacourse.movie.feature.model.movie.MovieListItem.MovieUiModel

interface MovieSelectionContract {
    interface View {
        fun showMovies(movies: List<MovieListItem>)

        fun goToReservation(movie: MovieUiModel)
    }

    interface Presenter {
        fun initializeMovies()

        fun selectMovie(movie: MovieUiModel)
    }
}

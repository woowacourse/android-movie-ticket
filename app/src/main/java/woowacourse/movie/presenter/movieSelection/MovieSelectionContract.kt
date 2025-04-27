package woowacourse.movie.presenter.movieSelection

import woowacourse.movie.view.model.MovieListItem
import woowacourse.movie.view.model.MovieListItem.MovieUiModel

interface MovieSelectionContract {
    interface View {
        fun showMovies(movies: List<MovieListItem>)

        fun goToReservation(movie: MovieUiModel)
    }

    interface Presenter {
        fun loadMovies()

        fun onMovieSelection(movie: MovieUiModel)
    }
}

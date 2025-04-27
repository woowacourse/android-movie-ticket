package woowacourse.movie.presenter.movieSelection

import woowacourse.movie.view.model.movie.MovieListItem
import woowacourse.movie.view.model.movie.MovieListItem.MovieUiModel

interface MovieSelectionContract {
    interface View {
        fun showMovies(movies: List<MovieListItem>)

        fun goToReservation(movie: MovieUiModel)
    }

    interface Presenter {
        fun onViewCreated()

        fun onMovieSelection(movie: MovieUiModel)
    }
}

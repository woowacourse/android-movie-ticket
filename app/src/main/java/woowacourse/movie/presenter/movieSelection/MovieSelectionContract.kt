package woowacourse.movie.presenter.movieSelection

import woowacourse.movie.view.model.MovieUiModel

interface MovieSelectionContract {
    interface View {
        fun showMovies(movies: List<MovieUiModel>)

        fun selectMovie(movie: MovieUiModel)
    }

    interface Presenter {
        fun loadMovies()

        fun onMovieSelection(movie: MovieUiModel)
    }
}

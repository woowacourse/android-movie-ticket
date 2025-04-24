package woowacourse.movie.presentation.view.movies

import woowacourse.movie.presentation.model.MovieUiModel

interface OnMovieEventListener {
    fun onClick(movie: MovieUiModel)
}

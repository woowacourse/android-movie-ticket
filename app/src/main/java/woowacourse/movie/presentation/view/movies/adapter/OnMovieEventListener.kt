package woowacourse.movie.presentation.view.movies.adapter

import woowacourse.movie.presentation.model.MovieUiModel

interface OnMovieEventListener {
    fun onClick(movie: MovieUiModel)
}

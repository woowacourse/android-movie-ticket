package woowacourse.movie.presentation.view.movies

import woowacourse.movie.domain.model.Movie

interface OnMovieEventListener {
    fun onClick(movie: Movie)
}

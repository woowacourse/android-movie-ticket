package woowacourse.movie.view.movies

import woowacourse.movie.domain.model.Movie

interface OnMovieEventListener {
    fun onReserveButtonClick(movie: Movie)
}

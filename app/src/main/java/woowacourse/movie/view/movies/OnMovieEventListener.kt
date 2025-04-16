package woowacourse.movie.view.movies

import woowacourse.movie.model.Movie

interface OnMovieEventListener {
    fun onClick(movie: Movie)
}
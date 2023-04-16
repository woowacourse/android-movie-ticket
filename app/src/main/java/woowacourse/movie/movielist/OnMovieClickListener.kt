package woowacourse.movie.movielist

import woowacourse.movie.domain.movieinfo.Movie

interface OnMovieClickListener {

    fun onMovieClick(movie: Movie)
}

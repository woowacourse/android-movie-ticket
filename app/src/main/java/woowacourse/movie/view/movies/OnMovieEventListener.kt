package woowacourse.movie.view.movies

import woowacourse.movie.domain.Movie

interface OnMovieEventListener {
    fun onClickReservation(movie: Movie)
}

package woowacourse.movie.view

import woowacourse.movie.domain.Movie

interface OnMovieEventListener {
    fun onClickReservation(movie: Movie)
}

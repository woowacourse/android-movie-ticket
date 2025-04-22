package woowacourse.movie.view.movie

import woowacourse.movie.model.Movie

interface MovieClickListener {
    fun onReservationClick(movie: Movie)
}

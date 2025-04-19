package woowacourse.movie.view

import woowacourse.movie.model.Movie

interface MovieClickListener {
    fun onReservationClick(movie: Movie)
}

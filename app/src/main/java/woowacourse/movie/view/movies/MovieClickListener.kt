package woowacourse.movie.view.movies

import woowacourse.movie.model.Movie

interface MovieClickListener {
    fun onReservationClick(movie: Movie)
}

package woowacourse.movie.movielist

import woowacourse.movie.domain.Movie

interface ClickListener {
    fun onReserveClick(movie: Movie)
}

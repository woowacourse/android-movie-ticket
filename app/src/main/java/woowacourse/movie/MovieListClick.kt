package woowacourse.movie

import woowacourse.movie.domain.Movie

interface MovieListClick {
    fun navigateToBook(movie: Movie)
}

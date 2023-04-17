package woowacourse.movie.domain.data

import woowacourse.movie.domain.movieinfo.Movie

interface MovieData {
    fun getAll(): List<Movie>
}

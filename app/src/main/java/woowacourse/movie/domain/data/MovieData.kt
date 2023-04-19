package woowacourse.movie.domain.data

import woowacourse.movie.domain.Movie

interface MovieData {
    fun getAll(): List<Movie>
}

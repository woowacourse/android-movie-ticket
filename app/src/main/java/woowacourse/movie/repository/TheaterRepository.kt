package woowacourse.movie.repository

import woowacourse.movie.model.theater.Theater

interface TheaterRepository {
    fun getTheaters(): List<Theater>
}

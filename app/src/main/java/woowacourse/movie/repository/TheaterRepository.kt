package woowacourse.movie.repository

import woowacourse.movie.model.Theater
import woowacourse.movie.model.seat.Position

interface TheaterRepository {
    fun getTheater(): Theater
}

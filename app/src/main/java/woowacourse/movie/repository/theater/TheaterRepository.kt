package woowacourse.movie.repository.theater

import woowacourse.movie.model.Theater

interface TheaterRepository {
    fun getTheater(): Theater
}

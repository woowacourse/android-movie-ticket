package woowacourse.movie.repository.theater

import woowacourse.movie.model.Theater
import woowacourse.movie.repository.theater.TheaterRepository

class PseudoTheaterRepository : TheaterRepository {
    override fun getTheater(): Theater = Theater(5, 4)
}

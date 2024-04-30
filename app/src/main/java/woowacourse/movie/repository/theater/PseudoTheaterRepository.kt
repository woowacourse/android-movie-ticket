package woowacourse.movie.repository.theater

import woowacourse.movie.model.Theater

class PseudoTheaterRepository : TheaterRepository {
    override fun getTheater(): Theater = Theater(5, 4)
}

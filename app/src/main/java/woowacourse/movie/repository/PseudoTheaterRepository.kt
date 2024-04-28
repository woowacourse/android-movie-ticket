package woowacourse.movie.repository

import woowacourse.movie.model.Theater

class PseudoTheaterRepository : TheaterRepository {
    override fun getTheater(): Theater = Theater(4, 5)
}

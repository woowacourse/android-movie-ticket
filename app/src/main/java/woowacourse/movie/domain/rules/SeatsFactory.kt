package woowacourse.movie.domain.rules

import woowacourse.movie.domain.Seat

interface SeatsFactory {
    fun get(): List<Seat>
}

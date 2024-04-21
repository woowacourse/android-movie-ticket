package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.model.Reservation2
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.Screen2

interface ReservationRepository {
    fun save(
        screen: Screen,
        count: Int,
    ): Result<Int>

    fun findById(id: Int): Result<Reservation>
}

interface ReservationRepository2 {
    fun save(
        screen: Screen2,
        count: Int,
    ): Result<Int>

    fun findById(id: Int): Result<Reservation2>
}

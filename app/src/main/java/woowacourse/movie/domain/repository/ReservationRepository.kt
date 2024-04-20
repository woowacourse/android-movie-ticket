package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.model.Screen

interface ReservationRepository {
    fun save(
        screen: Screen,
        count: Int,
    ): Result<Int>

    fun findByReservationId(id: Int): Result<Reservation>
}

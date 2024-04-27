package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.model.Reservation2
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.Seats

interface ReservationRepository {
    fun save(
        screen: Screen,
        count: Int,
    ): Result<Int>

    fun save(
        screen: Screen,
        seat: Seats,
    ): Result<Int>

    fun findById(id: Int): Result<Reservation>

    fun findById2(id: Int): Result<Reservation2>
}

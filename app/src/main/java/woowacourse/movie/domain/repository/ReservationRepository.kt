package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.model.Screen

interface ReservationRepository {
    fun save(
        screen: Screen,
        count: Int,
    ): Result<Int>

    fun findById(id: Int): Result<Reservation>
}

interface ReservationRepository2 {
    fun save(
        screen: Screen,
        count: Int,
    ): Result<Int>

    fun findById(id: Int): Result<Reservation>
}

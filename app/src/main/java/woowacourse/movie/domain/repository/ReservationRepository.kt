package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.DateTime
import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.Seats
import woowacourse.movie.domain.model.TimeReservation

interface ReservationRepository {
    fun save(
        screen: Screen,
        seats: Seats,
        dateTime: DateTime,
    ): Result<Int>

    fun saveTimeReservation(
        screen: Screen,
        count: Int,
        dateTime: DateTime,
    ): Result<Int>

    fun loadTimeReservation(timeReservationId: Int): TimeReservation

    fun findById(id: Int): Result<Reservation>
}

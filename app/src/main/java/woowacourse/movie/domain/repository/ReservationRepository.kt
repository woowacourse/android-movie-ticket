package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.Seat
import java.time.LocalDateTime

interface ReservationRepository {
    fun saveReservation(
        screen: Screen,
        ticketCount: Int,
        seats: List<Seat>,
        dateTime: LocalDateTime,
    ): Result<Int>

    fun findByReservationId(id: Int): Result<Reservation>
}

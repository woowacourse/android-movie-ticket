package woowacourse.movie.data

import woowacourse.movie.domain.reservation.Quantity
import woowacourse.movie.domain.reservation.Reservation
import woowacourse.movie.domain.screening.Screening

interface ReservationRepository {
    fun findAll(): List<Reservation>

    fun find(id: Long): Reservation?

    fun save(
        screening: Screening,
        quantity: Quantity,
    ): Long
}

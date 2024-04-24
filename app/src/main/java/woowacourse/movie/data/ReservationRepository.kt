package woowacourse.movie.data

import woowacourse.movie.model.Quantity
import woowacourse.movie.model.Reservation
import woowacourse.movie.model.screening.Screening

interface ReservationRepository {
    fun findAll(): List<Reservation>

    fun find(id: Long): Reservation?

    fun save(
        screening: Screening,
        quantity: Quantity,
    ): Long
}

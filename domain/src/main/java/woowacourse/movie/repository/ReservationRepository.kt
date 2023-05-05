package woowacourse.movie.repository

import woowacourse.movie.domain.screening.Reservation

object ReservationRepository {

    private var next_id: Long = 1L
    private val reservations: MutableMap<Long, Reservation> = mutableMapOf()

    fun save(reservation: Reservation) {
        if (reservation.id == null) reservation.id = next_id++
        reservations[reservation.id!!] = reservation
    }

    fun findById(id: Long): Reservation? {
        return reservations[id]
    }
}

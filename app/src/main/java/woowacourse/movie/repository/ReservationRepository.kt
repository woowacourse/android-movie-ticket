package woowacourse.movie.repository

import woowacourse.movie.model.Reservation

interface ReservationRepository {
    fun getReservation(id: Int): Reservation?

    fun putReservation(reservation: Reservation): Int
}

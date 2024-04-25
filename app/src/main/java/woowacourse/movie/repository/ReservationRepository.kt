package woowacourse.movie.repository

import woowacourse.movie.model.Reservation

interface ReservationRepository {
    fun getLastReservation(): Reservation?

    fun putReservation(reservation: Reservation)
}

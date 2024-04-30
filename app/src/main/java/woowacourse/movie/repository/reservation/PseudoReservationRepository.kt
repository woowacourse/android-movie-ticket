package woowacourse.movie.repository.reservation

import woowacourse.movie.model.Reservation

class PseudoReservationRepository : ReservationRepository {
    override fun getLastReservation(): Reservation? = reservations.lastOrNull()

    override fun putReservation(reservation: Reservation) {
        reservations.add(reservation)
    }

    companion object {
        private val reservations: MutableList<Reservation> = mutableListOf()
    }
}

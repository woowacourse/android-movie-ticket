package woowacourse.movie.repository

import woowacourse.movie.model.Reservation

class PseudoReservationRepository : ReservationRepository {
    override fun getReservation(id: Int): Reservation? = reservations.getOrNull(id)

    override fun putReservation(reservation: Reservation): Int {
        reservations.add(reservation)
        return reservations.lastIndex
    }

    companion object {
        private val reservations: MutableList<Reservation> = mutableListOf()
    }
}

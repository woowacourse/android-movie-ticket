package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.Ticket

object DummyReservation : ReservationRepository {
    private val reservations = mutableListOf<Reservation>()

    override fun save(
        screen: Screen,
        count: Int,
    ): Result<Int> {
        return runCatching {
            val id = reservations.size + 1
            reservations.add(Reservation(id, screen, Ticket(count)))
            id
        }
    }

    override fun findById(id: Int): Result<Reservation> {
        return runCatching {
            val reservation = reservations.find { it.id == id }
            reservation ?: throw IllegalArgumentException("예약 정보를 찾을 수 없습니다.")
        }
    }
}

object DummyReservation2 : ReservationRepository2 {
    private val reservations = mutableListOf<Reservation>()

    override fun save(
        screen: Screen,
        count: Int,
    ): Result<Int> {
        return runCatching {
            val id = reservations.size + 1
            reservations.add(Reservation(id, screen, Ticket(count)))
            id
        }
    }

    override fun findById(id: Int): Result<Reservation> {
        return runCatching {
            val reservation = reservations.find { it.id == id }
            reservation ?: throw IllegalArgumentException("예약 정보를 찾을 수 없습니다.")
        }
    }
}

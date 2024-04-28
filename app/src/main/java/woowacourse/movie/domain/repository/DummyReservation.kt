package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.DateTime
import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.model.Reservation2
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.Seats
import woowacourse.movie.domain.model.Ticket
import woowacourse.movie.domain.model.TimeReservation

object DummyReservation : ReservationRepository {
    private val reservations = mutableListOf<Reservation>()

    private val timeReservation = mutableListOf<TimeReservation>()

    private val reservations2 = mutableListOf<Reservation2>()

    // TODO: delete method (refactoring)
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

    override fun save(
        screen: Screen,
        seats: Seats,
    ): Result<Int> {
        return runCatching {
            val id = reservations2.size + 1
            reservations2.add(Reservation2(id, screen, Ticket(seats.count()), seats))
            id
        }
    }

    override fun saveTimeReservation(screen: Screen, count: Int, dateTime: DateTime): Result<Int> {
        return runCatching {
            val id = timeReservation.size + 1
            timeReservation.add(TimeReservation(id, screen, Ticket(count), dateTime))
            id
        }
    }

    // TODO: delete method (refactoring)
    override fun findById(id: Int): Result<Reservation> {
        return runCatching {
            val reservation = reservations.find { it.id == id }
            reservation ?: throw IllegalArgumentException("예약 정보를 찾을 수 없습니다.")
        }
    }

    override fun findById2(id: Int): Result<Reservation2> {
        return runCatching {
            val reservation = reservations2.find { it.id == id }
            reservation ?: throw IllegalArgumentException("예약 정보를 찾을 수 없습니다.")
        }
    }
}

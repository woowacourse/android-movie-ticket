package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.DateTime
import woowacourse.movie.domain.model.Reservation2
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.Seats
import woowacourse.movie.domain.model.Ticket
import woowacourse.movie.domain.model.TimeReservation

object DummyReservation : ReservationRepository {
    private val timeReservation = mutableListOf<TimeReservation>()

    private val reservations2 = mutableListOf<Reservation2>()

    override fun save(
        screen: Screen,
        seats: Seats,
        dateTime: DateTime
    ): Result<Int> {
        return runCatching {
            val id = reservations2.size + 1
            reservations2.add(Reservation2(id, screen, Ticket(seats.count()), seats, dateTime))
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

    override fun loadTimeReservation(timeReservationId: Int): TimeReservation =
        timeReservation.find { it.id == timeReservationId } ?: throw NoSuchElementException("TimeReservation not found with timeReservationId: $timeReservationId.")

    override fun findById2(id: Int): Result<Reservation2> {
        return runCatching {
            val reservation = reservations2.find { it.id == id }
            reservation ?: throw IllegalArgumentException("예약 정보를 찾을 수 없습니다.")
        }
    }
}

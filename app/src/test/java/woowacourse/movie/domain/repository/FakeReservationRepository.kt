package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.DateTime
import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.Seats
import woowacourse.movie.domain.model.Ticket
import woowacourse.movie.domain.model.TimeReservation

class FakeReservationRepository : ReservationRepository {
    private val reservations =
        mutableListOf(
            Reservation.NULL,
        )

    private val timeReservations =
        mutableListOf(
            TimeReservation.NULL,
        )

    override fun save(
        screen: Screen,
        seats: Seats,
        dateTime: DateTime,
    ): Result<Int> {
        TODO("Not yet implemented")
    }

    override fun saveTimeReservation(
        screen: Screen,
        count: Int,
        dateTime: DateTime,
    ): Result<Int> =
        runCatching {
            val id = timeReservations.size + 1
            timeReservations.add(
                TimeReservation(
                    id = timeReservations.size + 1,
                    screen = screen,
                    ticket = Ticket(count),
                    dateTime = dateTime,
                ),
            )
            id
        }

    override fun loadTimeReservation(timeReservationId: Int): TimeReservation =
        timeReservations.find { it.id == timeReservationId }
            ?: throw NoSuchElementException("TimeReservation not found with timeReservationId: $timeReservationId.")

    override fun findById(id: Int): Result<Reservation> =
        runCatching {
            val reservation = reservations.find { it.id == id }
            reservation ?: throw NoSuchElementException()
        }
}

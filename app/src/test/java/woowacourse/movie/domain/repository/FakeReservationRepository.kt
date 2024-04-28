package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.DateTime
import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.model.Reservation2
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.Seats
import woowacourse.movie.domain.model.Ticket
import woowacourse.movie.domain.model.TimeReservation

class FakeReservationRepository : ReservationRepository {
    private val reservations =
        mutableListOf(
            Reservation2.NULL,
        )

    private val timeReservations =
        mutableListOf(
            TimeReservation.NULL,
        )

    // TODO: delete this method
    override fun save(
        screen: Screen,
        count: Int,
    ): Result<Int> {
        TODO("Not yet implemented")
    }

    override fun save(
        screen: Screen,
        seats: Seats,
    ): Result<Int> =
        runCatching {
            val id = reservations.size + 1
            reservations.add(
                Reservation2(
                    id = reservations.size + 1,
                    screen = screen,
                    ticket = Ticket(seats.count()),
                    seats = seats,
                ),
            )
            id
        }

    override fun saveTimeReservation(screen: Screen, count: Int, dateTime: DateTime): Result<Int> = runCatching {
        val id = timeReservations.size + 1
        timeReservations.add(
            TimeReservation(
                id = timeReservations.size + 1,
                screen = screen,
                ticket = Ticket(count),
                dateTime = dateTime,
            )
        )
        id
    }

    // TODO: delete this method
    override fun findById(id: Int): Result<Reservation> {
        TODO("Not yet implemented")
    }

    override fun findById2(id: Int): Result<Reservation2> =
        runCatching {
            val reservation = reservations.find { it.id == id }
            reservation ?: throw NoSuchElementException()
        }
}

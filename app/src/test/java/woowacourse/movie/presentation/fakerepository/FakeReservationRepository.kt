package woowacourse.movie.presentation.fakerepository

import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.repository.ReservationRepository

class FakeReservationRepository(
    private val id: Int,
    private val reservation: Reservation,
) : ReservationRepository {
    override fun saveReservation(
        screen: Screen,
        count: Int,
    ): Result<Int> {
        return runCatching { id }
    }

    override fun findByReservationId(id: Int): Result<Reservation> {
        return runCatching {
            if (id != this.id) throw NoSuchElementException()
            reservation
        }
    }
}

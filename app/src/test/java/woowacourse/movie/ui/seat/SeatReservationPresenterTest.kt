package woowacourse.movie.ui.seat

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.Grade
import woowacourse.movie.domain.model.Position
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.Seats
import woowacourse.movie.domain.repository.FakeReservationRepository
import woowacourse.movie.domain.repository.FakeScreenRepository

class SeatReservationPresenterTest {
    private lateinit var mockView: SeatReservationContract.View
    private lateinit var presenter: SeatReservationPresenter

    @BeforeEach
    fun setUp() {
        mockView = mockk<SeatReservationContract.View>()
        presenter =
            SeatReservationPresenter(
                view = mockView,
                screenRepository = FakeScreenRepository(),
                reservationRepository = FakeReservationRepository(),
            )
    }

    @Test
    fun showSeats() {
        // given
        val fakeSeats =
            Seats(
                Seat(Position(0, 0), Grade.S),
                Seat(Position(1, 1), Grade.A),
                Seat(Position(2, 2), Grade.B),
            )
        every { mockView.showSeats(fakeSeats) } just runs

        // when
        presenter.loadSeats(1)

        // then
        verify(exactly = 1) { mockView.showSeats(fakeSeats) }
    }
}

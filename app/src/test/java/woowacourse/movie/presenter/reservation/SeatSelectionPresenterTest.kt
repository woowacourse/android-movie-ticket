package woowacourse.movie.presenter.reservation

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import woowacourse.movie.db.screening.ScreeningDao
import woowacourse.movie.db.seats.SeatsDao
import woowacourse.movie.model.seats.Grade
import woowacourse.movie.model.seats.Seat
import woowacourse.movie.model.seats.Seats

@ExtendWith(MockKExtension::class)
class SeatSelectionPresenterTest {
    @MockK
    private lateinit var view: SeatSelectionContract.View
    private lateinit var presenter: SeatSelectionContract.Presenter

    @BeforeEach
    fun setUp() {
        presenter = SeatSelectionPresenter(view, SeatsDao(), ScreeningDao())
        with(presenter) {
            manageSelectedSeats(true, 0, Seat('A', 1, Grade.B))
            manageSelectedSeats(true, 0, Seat('C', 1, Grade.S))
            manageSelectedSeats(true, 0, Seat('E', 1, Grade.A))
        }
    }

    @Test
    fun `좌석별 번호를 보여준다`() {
        every { view.initializeSeatsTable(any(), any()) } just runs
        presenter.loadSeatNumber()
        verify(exactly = 20) { view.initializeSeatsTable(any(), any()) }
    }

    @Test
    fun `영화 제목을 보여준다`() {
        every { view.showMovieTitle(any()) } just runs
        presenter.loadMovie(0)
        verify { view.showMovieTitle(any()) }
    }

    @Test
    fun `총 결제 금액을 보여준다`() {
        every { view.showAmount(any()) } just runs
        presenter.updateTotalPrice(true, Seat('E', 1, Grade.A))
        verify { view.showAmount(37_000) }
    }

    @Test
    fun `확인 버튼을 누르면 예매 진행 여부를 묻는 다이얼로그를 띄운다 `() {
        every { view.launchReservationConfirmDialog(any()) } just runs
        presenter.initializeConfirmButton()
        verify { view.launchReservationConfirmDialog(any()) }
    }

    @Test
    fun `선택된 좌석들을 복구한다`() {
        every { view.restoreSelectedSeats(any()) } just runs
        presenter.restoreSeats(Seats(), emptyList())
        verify { view.restoreSelectedSeats(any()) }
    }

    @Test
    fun `예약 상태를 복구한다`() {
        every { view.setConfirmButtonEnabled(any()) } just runs
        every { view.showAmount(37_000) } just runs
        presenter.restoreReservation(3)
        verify { view.setConfirmButtonEnabled(any()) }
        verify { view.showAmount(37_000) }
    }
}

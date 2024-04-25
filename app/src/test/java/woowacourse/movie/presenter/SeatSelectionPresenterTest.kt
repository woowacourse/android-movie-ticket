package woowacourse.movie.presenter

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import woowacourse.movie.db.ScreeningDao
import woowacourse.movie.db.SeatsDao
import woowacourse.movie.model.Grade
import woowacourse.movie.model.Seat
import woowacourse.movie.presenter.reservation.SeatSelectionContract
import woowacourse.movie.presenter.reservation.SeatSelectionPresenter

@ExtendWith(MockKExtension::class)
class SeatSelectionPresenterTest {
    @MockK
    private lateinit var view: SeatSelectionContract.View
    private lateinit var presenter: SeatSelectionContract.Presenter

    @BeforeEach
    fun setUp() {
        presenter = SeatSelectionPresenter(view, SeatsDao(), ScreeningDao())
    }

    @Test
    fun `좌석별 번호를 보여준다`() {
        every { view.showSeatNumber(any(), any()) } just runs
        presenter.loadSeatNumber()
        verify(exactly = 20) { view.showSeatNumber(any(), any()) }
    }

    @Test
    fun `영화 제목을 보여준다`() {
        every { view.showMovieTitle(any()) } just runs
        presenter.loadMovie(0)
        verify { view.showMovieTitle(any()) }
    }

    @Test
    fun `총 결제 금액을 보여준다`() {
        every { view.showTotalPrice(any()) } just runs
        with(presenter) {
            updateTotalPrice(true, Seat('A', 1, Grade.B))
            updateTotalPrice(true, Seat('C', 1, Grade.S))
            updateTotalPrice(true, Seat('E', 1, Grade.A))
        }
        verify { view.showTotalPrice(37_000) }
    }

    @Test
    fun `확인 버튼을 누르면 예매 진행 여부를 묻는 다이얼로그를 띄운다 `() {
        every { view.launchReservationConfirmDialog(any(), any()) } just runs
        presenter.initializeConfirmButton()
        verify { view.launchReservationConfirmDialog(any(), any()) }
    }
}

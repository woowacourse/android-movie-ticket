package woowacourse.movie.presenter

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.Ticket
import woowacourse.movie.fixture.HARRY_POTTER_01
import woowacourse.movie.view.reservation.model.TicketUiModel
import woowacourse.movie.view.reservation.model.toDomain
import woowacourse.movie.view.reservation.model.toUiModel
import woowacourse.movie.view.seat.SeatSelectContract
import woowacourse.movie.view.seat.SeatSelectPresenter
import woowacourse.movie.view.seat.model.SeatUiModel

class SeatSelectPresenterTest {
    private lateinit var presenter: SeatSelectContract.Presenter
    private lateinit var view: SeatSelectContract.View
    private lateinit var ticket: TicketUiModel

    @BeforeEach
    fun setup() {
        ticket = Ticket(HARRY_POTTER_01).toUiModel()
        view = mockk(relaxed = true)
        presenter = SeatSelectPresenter(view, ticket)
    }

    @Test
    fun `영화 정보와 총 가격을 표시한다`() {
        presenter.loadSeatSelectScreen()

        verify { view.showMovieInfo(ticket.movie) }
        verify { view.showTotalPrice(ticket.toDomain().totalPrice()) }
    }

    @Test
    fun `좌석을 선택하고 다시 클릭하면 해제된다`() {
        // given
        val seat = SeatUiModel(0, 0)
        presenter.onClickSeat(seat)

        // when
        presenter.onClickSeat(seat) // 같은 좌석을 다시 클릭

        // then
        verify(exactly = 1) { view.updateSeatSelectionState(seat, true) } // 첫 클릭 -> 선택됨
        verify(exactly = 1) { view.updateSeatSelectionState(seat, false) } // 두 번째 클릭 -> 해제됨
        verify(atLeast = 2) { view.updateConfirmButtonState(any()) }
        verify(atLeast = 2) { view.showTotalPrice(any()) }
    }

    @Test
    fun `확인 버튼을 클릭하면 확인 다이얼로그를 보여준다`() {
        // when
        presenter.onClickConfirmButton()

        // then
        verify { view.showConfirmAlertDialog() }
    }

    @Test
    fun `예매 완료 버튼을 클릭하면 예매 완료 화면으로 이동한다`() {
        presenter.completeReservation()

        verify { view.navigateToCompleteScreen(any()) }
    }
}

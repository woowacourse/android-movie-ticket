package woowacourse.movie.presenter

import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import woowacourse.movie.contract.SeatSelectionContract
import woowacourse.movie.model.seat.Position
import woowacourse.movie.repository.PseudoTheaterRepository
import woowacourse.movie.repository.TheaterRepository

@ExtendWith(MockKExtension::class)
class PositionSelectionPresenterTest {

    @RelaxedMockK
    lateinit var view: SeatSelectionContract.View

    @RelaxedMockK
    lateinit var theaterRepository: TheaterRepository

    lateinit var presenter: SeatSelectionPresenter

    @BeforeEach
    fun setUp() {
        presenter = SeatSelectionPresenter(view, theaterRepository, 3)
    }

    @Test
    fun `좌석을 표기할 수 있어야 한다`() {
        presenter.loadTheater()
        verify {
            view.displayTheater(any())
        }
    }

    @Test
    fun `선택되지 않은 좌석을 선택했을 때 좌석을 선택 상태로 표기해야 한다`() {
        every { view.displayTheater(any()) } just runs

        val position = Position(1, 1)
        presenter.toggleSeatSelection(position)

        verify { view.displaySelectedSeat(position) }
    }

    @Test
    fun `이미 선택된 좌석을 선택했을 때 좌석을 선택 안됨 상태로 표기해야 한다`() {
        every { view.displayTheater(any()) } just runs
        val position = Position(1, 1)
        presenter.toggleSeatSelection(position)

        presenter.toggleSeatSelection(position)

        verify { view.displayDeSelectedSeat(position) }
    }

    @Test
    fun `티켓 수만큼 좌석을 선택할 수 있다`() {
        every { view.displayTheater(any()) } just runs
        presenter = SeatSelectionPresenter(view, theaterRepository, 3)

        val position1 = Position(1, 1)
        val position2 = Position(2, 1)
        val position3 = Position(3, 1)
        val position4 = Position(4, 1)
        presenter.toggleSeatSelection(position1)
        presenter.toggleSeatSelection(position2)
        presenter.toggleSeatSelection(position3)
        presenter.toggleSeatSelection(position4)

        verify(exactly = 0) { view.displayDeSelectedSeat(position4) }
    }

    @Test
    fun `티켓 수만큼 좌석을 선택하면 확인 버튼이 활성화 된다`() {
        every { view.displayTheater(any()) } just runs
        presenter = SeatSelectionPresenter(view, theaterRepository, 3)

        val position1 = Position(1, 1)
        val position2 = Position(2, 1)
        val position3 = Position(3, 1)
        presenter.toggleSeatSelection(position1)
        presenter.toggleSeatSelection(position2)
        presenter.toggleSeatSelection(position3)

        verify { view.activateConfirm() }
    }

    @Test
    fun `선택 해제를 하면 확인 버튼이 비활성화 된다`() {
        every { view.displayTheater(any()) } just runs
        presenter = SeatSelectionPresenter(view, theaterRepository, 1)
        val position1 = Position(1, 1)
        presenter.toggleSeatSelection(position1)

        presenter.toggleSeatSelection(position1)

        verify { view.deActivateConfirm() }
    }

    @Test
    fun `좌석을 선택하면 하단에 선택한 좌석 수를 반영한 최종 가격이 표시된다`() {
        every { view.displayTheater(any()) } just runs
        theaterRepository = PseudoTheaterRepository()
        presenter = SeatSelectionPresenter(view, theaterRepository, 2)
        presenter.loadTheater()

        val position1 = Position(1, 1) // B Tier Seat, 10000₩
        val position2 = Position(1, 3) // S Tier Seat, 15000₩
        presenter.toggleSeatSelection(position1)
        presenter.toggleSeatSelection(position2)

        verify { view.displayTicketPrice(25000) }
    }

    @Test
    fun `확인 버튼을 누르면 예매 확정 확인 다이얼로그를 표기한다`() {
        every { view.displayTheater(any()) } just runs
        presenter.askConfirm()
        verify { view.displayConfirmDialog() }
    }

    @Test
    fun `예매 확정 확인 다이얼로그에서 확인 버튼을 누르면 결제 확인 화면으로 넘어간다`() {
        every { view.displayConfirmDialog() } just runs
        presenter.purchase()
        verify { view.navigateToPurchaseConfirmation() }
    }
}

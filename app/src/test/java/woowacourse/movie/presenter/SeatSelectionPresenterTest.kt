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
import woowacourse.movie.repository.TheaterRepository

@ExtendWith(MockKExtension::class)
class SeatSelectionPresenterTest {

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

        val position = Position(0, 0)
        presenter.toggleSeatSelection(position)

        verify { view.displaySelectedSeat(position) }
    }

    @Test
    fun `이미 선택된 좌석을 선택했을 때 좌석을 선택 안됨 상태로 표기해야 한다`() {
        every { view.displayTheater(any()) } just runs
        val position = Position(0, 0)
        presenter.toggleSeatSelection(position)

        presenter.toggleSeatSelection(position)

        verify { view.displayDeSelectedSeat(position) }
    }

    @Test
    fun `티켓 수만큼 좌석을 선택할 수 있다`() {
        every { view.displayTheater(any()) } just runs
        presenter = SeatSelectionPresenter(view, theaterRepository, 3)

        val position1 = Position(0, 0)
        val position2 = Position(1, 0)
        val position3 = Position(2, 0)
        val position4 = Position(3, 0)
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

        val position1 = Position(0, 0)
        val position2 = Position(1, 0)
        val position3 = Position(2, 0)
        presenter.toggleSeatSelection(position1)
        presenter.toggleSeatSelection(position2)
        presenter.toggleSeatSelection(position3)

        verify { view.activateConfirm() }
    }

    @Test
    fun `선택 해제를 하면 확인 버튼이 비활성화 된다`() {
        every { view.displayTheater(any()) } just runs
        presenter = SeatSelectionPresenter(view, theaterRepository, 1)
        val position1 = Position(0, 0)
        presenter.toggleSeatSelection(position1)
        presenter.toggleSeatSelection(position1)

        verify { view.deActivateConfirm() }
    }
}

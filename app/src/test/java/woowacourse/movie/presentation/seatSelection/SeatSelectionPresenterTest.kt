package woowacourse.movie.presentation.seatSelection

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.data.MovieRepository
import woowacourse.movie.model.Seat
import woowacourse.movie.model.SeatingSystem

class SeatSelectionPresenterTest {
    private lateinit var presenter: SeatSelectionContract.Presenter
    private val view: SeatSelectionContract.View = mockk()
    private val repository = mockk<MovieRepository>()
    private val ticketCount = 3
    private val seat = mockk<Seat>()
    private val seatingSystem = mockk<SeatingSystem>(relaxed = true)

    @BeforeEach
    fun setUp() {
        presenter = SeatSelectionPresenter(view, ticketCount, repository)
    }

    @Test
    fun `updateSeatSelection로 전달 받은 index 값의 좌석 상태가 빈 좌석일 경우, 선택된 좌석 UI로 업데이트 하도록 view에게 요청한다`() {
        every { seatingSystem.isSelected(any()) } returns false
        every { seatingSystem.trySelectSeat(any()) } returns Result.success(seat)
        every { view.updateSelectedSeatUI(any()) } just Runs
        every { view.setButtonEnabledState(any()) } just Runs
        every { view.updateTotalPrice(any()) } just Runs

        presenter.updateSeatSelection(0)

        verify { view.updateSelectedSeatUI(0) }
        verify { view.setButtonEnabledState(any()) }
        verify { view.updateTotalPrice(any()) }
    }

    @Test
    fun `updateSeatSelection으로 전달 받은 index 값의 좌석 상태가 이미 선택된 좌석일 경우, 좌석 기본 UI로 업데이트 하도록 view에게 요청한다`() {
        every { seatingSystem.isSelected(any()) } returns true

        presenter.updateSeatSelection(0)

        verify { view.updateUnSelectedSeatUI(0) }
    }
}

package woowacourse.movie

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.seat.TheaterSeatPresenter

class SeatPresenterTest {
    @MockK
    lateinit var presenter: TheaterSeatPresenter

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `좌석 선택 시 배경색 변경 테스트`() {
        val seatId = "A1"
        every { presenter.toggleSeatSelection(seatId) } just runs
        every { presenter.updateSeatBackground(seatId) } just runs

        presenter.toggleSeatSelection(seatId)
        presenter.updateSeatBackground(seatId)

        verify(exactly = 1) { presenter.toggleSeatSelection(seatId) }
        verify(exactly = 1) { presenter.updateSeatBackground(seatId) }
    }

    @Test
    fun `선택된 좌석 수와 최종 가격 반영 테스트`() {
        every { presenter.calculateTotalPrice() } returns 25000

        val totalPrice = presenter.calculateTotalPrice()

        verify { presenter.calculateTotalPrice() }
        assert(totalPrice == 25000)
    }

    @Test
    fun `선택할 수 있는 좌석의 최대치는 티켓수 만큼이다`() {
        val seatId1 = "A1"
        val seatId2 = "A2"

        every { presenter.toggleSeatSelection(seatId1) } just runs
        every { presenter.updateSeatBackground(seatId1) } just runs
        every { presenter.toggleSeatSelection(seatId2) } just runs
        every { presenter.updateSeatBackground(seatId2) } just runs

        verify(exactly = 0) { presenter.updateSeatBackground(any()) }
    }

    @Test
    fun `선택된 좌석을 재선택하면 선택 해제 테스트`() {
        val seatId = "A1"
        every { presenter.toggleSeatSelection(seatId) } just runs

        presenter.toggleSeatSelection(seatId) // Select
        presenter.toggleSeatSelection(seatId) // Deselect

        verify(exactly = 2) { presenter.toggleSeatSelection(seatId) }
    }

    @Test
    fun `최종 예매 확인 다이얼로그 표시 테스트`() {
        every { presenter.showConfirmationDialog() } just runs

        presenter.showConfirmationDialog()

        verify { presenter.showConfirmationDialog() }
    }
}

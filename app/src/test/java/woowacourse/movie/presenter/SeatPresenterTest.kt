package woowacourse.movie.presenter

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.fixture.oneByOneSeat
import woowacourse.movie.domain.model.seat.Seats
import woowacourse.movie.presenter.seat.SeatContract
import woowacourse.movie.presenter.seat.SeatPresenter
import woowacourse.movie.view.seat.model.coord.Column
import woowacourse.movie.view.seat.model.coord.Coordination
import woowacourse.movie.view.seat.model.coord.Row

class SeatPresenterTest {
    private lateinit var mockView: SeatContract.View
    private lateinit var mockSeats: Seats
    private lateinit var presenter: SeatPresenter

    private val limit = 2

    @BeforeEach
    fun setUp() {
        mockView = mockk(relaxed = true)
        mockSeats = mockk(relaxed = true)
        presenter = SeatPresenter(mockView, mockSeats)
    }

    @Test
    fun `좌석을 추가할 수 없으면 토스트 메시지를 보여준다`() {
        // given
        val position = Coordination(Column(1), Row(1))
        every { mockSeats.toggleSeat(any(), any()) } returns false

        // when
        presenter.changeSeat(position, limit)

        // then
        verify { mockView.showToast(limit) }
    }

    @Test
    fun `예매 버튼 클릭 시 선택된 좌석이 부족하면 토스트 메시지를 보여준다`() {
        // given
        every { mockSeats.isNotSelectDone(limit) } returns true

        // when
        presenter.onConfirmClicked(limit)

        // then
        verify { mockView.showToast(limit) }
        verify(exactly = 0) { mockView.moveToBookingComplete(any(), any()) }
    }

    @Test
    fun `예매 버튼 클릭 시 좌석이 충분하면 예매 완료 화면으로 이동한다`() {
        // given
        every { mockSeats.isNotSelectDone(limit) } returns false
        every { mockSeats.item } returns setOf(oneByOneSeat)
        every { mockSeats.bookingPrice() } returns 10000

        // when
        presenter.onConfirmClicked(limit)

        // then
        verify { mockView.moveToBookingComplete(any(), 10000) }
    }
}

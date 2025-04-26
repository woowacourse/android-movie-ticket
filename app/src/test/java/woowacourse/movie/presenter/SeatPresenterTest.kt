package woowacourse.movie.presenter

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.fixture.twoByThreeCoord
import woowacourse.movie.domain.fixture.twoByThreeSeat
import woowacourse.movie.domain.model.seat.Seats
import woowacourse.movie.presenter.seat.SeatContract
import woowacourse.movie.presenter.seat.SeatPresenter

class SeatPresenterTest {
    private lateinit var view: SeatContract.View
    private lateinit var model: Seats
    private lateinit var presenter: SeatPresenter

    @BeforeEach
    fun setUp() {
        view = mockk<SeatContract.View>(relaxed = true)
        model = mockk<Seats>(relaxed = true)
        presenter = SeatPresenter(view, model)
    }

    @Test
    fun `선택한 좌석이 이미 선택되어 있으면 좌석을 제거한다`() {
        // given
        every { model.isSelected(any()) } returns true

        // when
        presenter.changeSeat(twoByThreeCoord, limit = 3)

        // then
        verify { model.removeSeat(twoByThreeSeat) }
    }

    @Test
    fun `선택한 좌석이 추가되지 않았었다면 좌석을 추가한다`() {
        // given
        every { model.isSelected(any()) } returns false
        every { model.canSelect(any()) } returns true

        // when
        presenter.changeSeat(twoByThreeCoord, 2)

        // then
        verify { model.addSeat(twoByThreeSeat) }
    }

    @Test
    fun `클릭한 좌석이 선택됐던 좌석이 아니고 예매 가능한 인원수를 초과하면 토스트 메시지를 보여준다`() {
        // given
        val limit = 0
        every { model.isSelected(any()) } returns false
        every { model.canSelect(any()) } returns false

        // when
        presenter.changeSeat(twoByThreeCoord, limit)

        // then
        verify { view.showToast(limit) }
    }

    @Test
    fun `클릭한 좌석이 선택됐던 좌석이고 예매 가능한 인원수를 초과할 때 선택된 좌석을 해제한다`() {
        // given
        val limit = 0
        every { model.isSelected(any()) } returns true
        every { model.canSelect(any()) } returns false

        // when
        presenter.changeSeat(twoByThreeCoord, limit)

        // then
        verify { model.removeSeat(twoByThreeSeat) }
    }

    @Test
    fun `좌석을 추가하면 추가된 티켓의 가격을 출력한다`() {
        // given
        every { model.isSelected(any()) } returns false
        every { model.canSelect(any()) } returns true
        every { model.bookingPrice() } returns 10_000

        // when
        presenter.changeSeat(twoByThreeCoord, 2)

        // then
        verify(exactly = 1) { view.showPrice(10_000) }
    }

    @Test
    fun `좌석을 취소하면 취소된 티켓의 가격을 출력한다`() {
        // given
        every { model.isSelected(any()) } returns true
        every { model.canSelect(any()) } returns true
        every { model.bookingPrice() } returns 10_000

        // when
        presenter.changeSeat(twoByThreeCoord, 2)

        // then
        verify { view.showPrice(0) }
    }
}

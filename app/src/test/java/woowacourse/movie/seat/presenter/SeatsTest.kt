package woowacourse.movie.seat.presenter

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.seats.contract.SeatsContract
import woowacourse.movie.seats.presenter.SeatsPresenter

class SeatsTest {
    private lateinit var view: SeatsContract.View
    private lateinit var presenter: SeatsContract.Presenter

    @BeforeEach
    fun setUp() {
        view = mockk<SeatsContract.View>()
        presenter = SeatsPresenter(view)
    }

    @Test
    fun `좌석을 선택하면 가격에 반영되어야 한다`() {
        every { view.setTotalPrice(any()) } just runs
        // when
        presenter.createSeat(1, 1)
        presenter.setPriceInfo()
        // then
        verify { view.setTotalPrice(10_000) }
    }

    @Test
    fun `좌석을 선택하면 셀의 색깔이 노란색으로 바뀌어야 한다`() {
        every { view.setSeatCellBackgroundColor(any()) } just runs
        // when
        presenter.createSeat(1, 1)
        presenter.setSeatsCellsBackgroundColorInfo()
        // then
        verify { view.setSeatCellBackgroundColor(presenter.seat) }
    }
}

package woowacourse.movie.seats.presenter

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.seats.contract.SeatsContract
import woowacourse.movie.seats.model.SeatsDataSource.seat

class SeatsPresenterTest {
    private lateinit var view: SeatsContract.View
    private lateinit var presenter: SeatsContract.Presenter

    @BeforeEach
    fun setUp() {
        view = mockk<SeatsContract.View>()
        presenter = SeatsPresenter(view)
    }

    @Test
    fun `좌석을 선택하면 셀의 색깔이 노란색으로 바뀌고 총 가격이 갱신되어야 한다`() {
        every {
            seat.select()
            view.setSeatCellBackgroundColor(any())
            view.setTotalPrice(any())
        } just runs
        // when
        presenter.createSeat(1, 1)
        presenter.setSeatsCellsBackgroundColorInfo()
        // then
        verify {
            seat.select()
            view.setSeatCellBackgroundColor(seat)
            view.setTotalPrice(10000)
        }
    }
}

package woowacourse.movie.presenter

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.Ticket
import woowacourse.movie.fixture.HARRY_POTTER_01
import woowacourse.movie.view.reservation.model.toUiModel
import woowacourse.movie.view.result.ReservationResultContract
import woowacourse.movie.view.result.ReservationResultPresenter

class ReservationResultPresenterTest {
    private lateinit var presenter: ReservationResultContract.Presenter
    private lateinit var view: ReservationResultContract.View

    @BeforeEach
    fun setup() {
        view = mockk(relaxed = true)
        presenter = ReservationResultPresenter(view, Ticket(HARRY_POTTER_01).toUiModel())
    }

    @Test
    fun `영화 예매 정보를 표시한다`() {
        // when
        presenter.loadReservationResultScreen()

        // then
        verify { view.showTicketInfo(any()) }
        verify { view.showTotalPrice(any()) }
    }
}

package woowacourse.movie.presenter

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.fixtures.movie
import woowacourse.movie.fixtures.ticket
import woowacourse.movie.presenter.seatSelection.SeatSelectionContract
import woowacourse.movie.presenter.seatSelection.SeatSelectionPresenter

class SeatSelectionPresenterTest {
    private lateinit var presenter: SeatSelectionPresenter
    private lateinit var view: SeatSelectionContract.View

    @BeforeEach
    fun setup() {
        view = mockk(relaxed = true)
        presenter = SeatSelectionPresenter(view)
    }

    @Test
    fun `실행 시 좌석 정보를 표시한다`() {
        // when
        presenter.onViewCreated(ticket)

        // then
        verifyAll {
            view.showMovieTitle(any())
            view.showTotalPrice(any())
        }
    }

    @Test
    fun `좌석 선택 완료 시 예매 완료 화면으로 이동한다`() {
        // given
        presenter.onViewCreated(ticket)
        every { view.goToReservationResult(any(), any()) } just Runs

        // when
        presenter.onAlertConfirmation()

        // then
        verify { view.goToReservationResult(any(), any()) }
    }
}

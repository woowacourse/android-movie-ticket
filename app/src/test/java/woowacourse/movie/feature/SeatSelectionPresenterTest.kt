package woowacourse.movie.feature

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.feature.seatSelection.SeatSelectionContract
import woowacourse.movie.feature.seatSelection.SeatSelectionPresenter
import woowacourse.movie.fixtures.ticket

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
        presenter.loadReservationInfo(ticket)

        // then
        verifyAll {
            view.showMovieTitle(any())
            view.showTotalPrice(any())
        }
    }

    @Test
    fun `좌석 선택 완료 시 예매 완료 화면으로 이동한다`() {
        // given
        presenter.loadReservationInfo(ticket)
        every { view.goToReservationResult(any(), any()) } just Runs

        // when
        presenter.confirmSelection()

        // then
        verify { view.goToReservationResult(any(), any()) }
    }
}

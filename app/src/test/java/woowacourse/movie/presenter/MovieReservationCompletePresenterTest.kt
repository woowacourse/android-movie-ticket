package woowacourse.movie.presenter

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.Ticket
import woowacourse.movie.fixture.HARRY_POTTER_01
import woowacourse.movie.view.reservation.model.toUiModel
import woowacourse.movie.view.result.MovieReservationCompleteContract
import woowacourse.movie.view.result.MovieReservationCompletePresenter

class MovieReservationCompletePresenterTest {
    private lateinit var presenter: MovieReservationCompleteContract.Presenter
    private lateinit var view: MovieReservationCompleteContract.View

    @BeforeEach
    fun setup() {
        view = mockk(relaxed = true)
        presenter = MovieReservationCompletePresenter(view, Ticket(HARRY_POTTER_01).toUiModel())
    }

    @Test
    fun `영화 예매 정보를 표시한다`() {
        // when
        presenter.loadMovieReservationCompleteScreen()

        // then
        verify { view.showTicketInfo(any()) }
        verify { view.showTotalPrice(any()) }
    }
}

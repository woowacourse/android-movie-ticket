package woowacourse.movie.model.ui.complete

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.model.data.UserTicketsImpl
import woowacourse.movie.model.movie.ReservationDetail
import woowacourse.movie.model.movie.UserTicket
import woowacourse.movie.ui.complete.MovieReservationCompleteContract
import woowacourse.movie.ui.complete.MovieReservationCompletePresenter
import java.time.LocalDateTime

class MovieReservationCompletePresenterTest {
    private lateinit var presenter: MovieReservationCompletePresenter
    private lateinit var view: MovieReservationCompleteContract.View

    @BeforeEach
    fun setUp() {
        view = mockk<MovieReservationCompleteContract.View>(relaxed = true)
        presenter = MovieReservationCompletePresenter(view, UserTicketsImpl)
        UserTicketsImpl.save(
            UserTicket(
                "",
                LocalDateTime.of(2024, 3, 28, 10, 0),
                ReservationDetail(1),
            ),
        )
    }

    @Test
    fun `티켓 정보를 가져온다`() {
        // given

        // when
        presenter.loadTicket(0L)

        // then
        verify { view.showTicket(any()) }
    }

    @Test
    fun `티켓 정보가 없을 경우 에러를 보여준다`() {
        // given

        // when
        presenter.loadTicket(-1L)

        // then
        verify { view.showError(any()) }
    }

    @Test
    fun `에러를 처리한다`() {
        // given

        // when
        presenter.handleError(Throwable())

        // then
        verify { view.showError(any()) }
    }
}

package woowacourse.movie.model.ui.reservation

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.model.data.MovieContentsImpl
import woowacourse.movie.model.data.UserTicketsImpl
import woowacourse.movie.ui.reservation.MovieReservationContract
import woowacourse.movie.ui.reservation.MovieReservationPresenter
import java.time.LocalDate

class MovieReservationPresenterTest {
    private lateinit var presenter: MovieReservationPresenter
    private lateinit var view: MovieReservationContract.View

    @BeforeEach
    fun setUp() {
        view = mockk<MovieReservationContract.View>(relaxed = true)
        presenter = MovieReservationPresenter(view, MovieContentsImpl, UserTicketsImpl)
    }

    @Test
    fun `영화 정보를 표시한다`() {
        // given

        // when
        presenter.loadMovieContent(0L)

        // then
        verify { view.showMovieContent(any()) }
        verify { view.showMovieDateSelection(any()) }
    }

    @Test
    fun `영화 정보를 표시할수 없을 경우 에러를 표시한다`() {
        // given

        // when
        presenter.loadMovieContent(-1L)

        // then
        verify { view.showError(any()) }
    }

    @Test
    fun `예매 인원을 업데이트한다`() {
        // given

        // when
        presenter.updateReservationCount(3)

        // then
        verify { view.updateReservationCount(3) }
    }

    @Test
    fun `영화 날짜를 선택한다`() {
        // given

        // when
        presenter.selectDate(LocalDate.of(2024, 3, 1))

        // then
        verify { view.showMovieTimeSelection(any()) }
    }

    @Test
    fun `예매인이 2에서 1로 감소한다`() {
        // given
        presenter.updateReservationCount()
        presenter.increaseCount()

        // when
        presenter.decreaseCount()

        // then
        verify { view.updateReservationCount(1) }
    }

    @Test
    fun `예매인이 1일때에서 2로 증가된다`() {
        // given
        presenter.updateReservationCount()

        // when
        presenter.increaseCount()

        // then
        verify { view.updateReservationCount(2) }
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
